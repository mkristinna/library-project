package com.demo.libraryproject.service;


import com.demo.libraryproject.model.Event;
import com.demo.libraryproject.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    public Event findById(String id){
        Optional<Event> result = eventRepository.findById(id);
        Event event = null;

        if(result.isPresent()){
            event = result.get();
        } else {
            throw new RuntimeException("Cannot find event with id " + id);
        }
        return event;
    }

    public List<Event> findAll(){
        return eventRepository.findAll();
    }

    public void save(Event event){
        eventRepository.save(event);
    }

    public void deleteById(String id){
        eventRepository.deleteById(id);
    }
}
