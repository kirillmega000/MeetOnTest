package com.example.meetontest.repos;

import com.example.meetontest.entities.Meeting;
import org.springframework.data.repository.CrudRepository;

public interface MeetingRepository extends CrudRepository<Meeting, Long> {
}
