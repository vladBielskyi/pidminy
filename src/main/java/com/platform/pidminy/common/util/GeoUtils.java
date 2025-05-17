package com.platform.pidminy.common.util;

import com.platform.pidminy.entity.embeddable.LocationCoordinates;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

/**
 * Utility class for geographic operations
 */
public class GeoUtils {

    private static final GeometryFactory GEOMETRY_FACTORY = new GeometryFactory(new PrecisionModel(), 4326);

    /**
     * Converts latitude and longitude to a JTS Point with SRID 4326 (WGS84)
     *
     * @param latitude the latitude
     * @param longitude the longitude
     * @return Point object with SRID 4326
     */
    public static Point createPoint(double latitude, double longitude) {
        return GEOMETRY_FACTORY.createPoint(new Coordinate(longitude, latitude));
    }

    /**
     * Converts LocationCoordinates to a JTS Point with SRID 4326 (WGS84)
     *
     * @param coordinates the location coordinates
     * @return Point object with SRID 4326, or null if coordinates are null or incomplete
     */
    public static Point createPoint(LocationCoordinates coordinates) {
        if (coordinates == null || coordinates.getLatitude() == null || coordinates.getLongitude() == null) {
            return null;
        }
        return createPoint(coordinates.getLatitude(), coordinates.getLongitude());
    }

    /**
     * Extracts LocationCoordinates from a JTS Point
     *
     * @param point the JTS Point
     * @return LocationCoordinates object, or null if point is null
     */
    public static LocationCoordinates toLocationCoordinates(Point point) {
        if (point == null) {
            return null;
        }

        return LocationCoordinates.builder()
                .latitude(point.getY())
                .longitude(point.getX())
                .build();
    }

    /**
     * Calculates the approximate distance between two points in kilometers
     *
     * @param lat1 latitude of first point
     * @param lon1 longitude of first point
     * @param lat2 latitude of second point
     * @param lon2 longitude of second point
     * @return approximate distance in kilometers
     */
    public static double calculateDistanceKm(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Earth's radius in kilometers

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }
}
