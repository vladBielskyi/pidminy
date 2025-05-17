package com.platform.pidminy.entity.listener;

import com.platform.pidminy.common.util.GeoUtils;
import com.platform.pidminy.entity.BusinessLocation;
import com.platform.pidminy.entity.embeddable.LocationCoordinates;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

/**
 * Entity listener for geographic entities to ensure the Point field is synchronized
 * with LocationCoordinates
 */
public class BusinessLocationEntityListener {

    /**
     * Update geo location field for BusinessLocation before persist or update
     */
    @PrePersist
    @PreUpdate
    public void onBusinessLocationSave(BusinessLocation entity) {
        syncGeoFields(entity);
    }

    /**
     * Synchronize the location coordinates with geo location field for BusinessLocation
     */
    private void syncGeoFields(BusinessLocation entity) {
        LocationCoordinates coordinates = entity.getLocationCoordinates();

        if (coordinates != null && coordinates.getLatitude() != null && coordinates.getLongitude() != null) {
            entity.setGeoLocation(GeoUtils.createPoint(coordinates));
        }
    }
}
