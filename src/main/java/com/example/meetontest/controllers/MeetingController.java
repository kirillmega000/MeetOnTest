package com.example.meetontest.controllers;

import com.example.meetontest.entities.Meeting;
import com.example.meetontest.repos.MeetingRepository;
import com.example.meetontest.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Controller
@RequestMapping(path = "meetings")
public class MeetingController {
    @Autowired
    private MeetingRepository meetingRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public String viewMeetings(Model model) {
        model.addAttribute("meetings", meetingRepository.findAll());
        return "meetingView";
    }

    @GetMapping("/add")
    public String createMeeting(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "createMeeting";
    }

    @PostMapping("/add")
    public String createMeeting(@RequestParam String name, @RequestParam String about, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date, @RequestParam Long manager_id) {
        if (!userRepository.findById(manager_id).isPresent())
            return "createMeeting";

        Meeting meeting = new Meeting(name, about, Date.from(date.toInstant(ZoneOffset.UTC)), userRepository.findById(manager_id).get());
        meetingRepository.save(meeting);
        return "index";
    }
}
