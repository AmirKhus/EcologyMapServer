package com.example.vkr_server.service;

import com.example.vkr_server.model.Marker;
import com.example.vkr_server.repository.MarkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MarkerServiceIml implements MarkerService{
    @Autowired
    MarkerRepository markerRepository;

    @Override
    public Marker addMarker(Marker marker) {
        return markerRepository.save(marker);
    }

    @Override
    public List<Marker> getAllMarker() {
        return markerRepository.findAll();
    }

    @Override
    public void deleteMarkerById(Long id) {
        markerRepository.deleteById(id);
    }

    @Override
    public List<Marker> getMarkerByUser(String user) {
        return markerRepository.findByAuthor(user);
    }

    @Override
    public List<Marker> getMarkerByRegion(String region) {
        return markerRepository.findByRegion(region);
    }


}
