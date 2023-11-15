package com.example.vkr_server.service;

import com.example.vkr_server.model.Marker;

import java.util.List;

public interface MarkerService {
    Marker addMarker(Marker marker);
    List<Marker> getAllMarker();
    void deleteMarkerById(Long id);
    List<Marker> getMarkerByUser(String user);

    List<Marker> getMarkerByRegion(String region);
}
