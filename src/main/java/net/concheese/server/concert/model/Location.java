package net.concheese.server.concert.model;

import java.util.List;
import java.util.UUID;

/**
 * {@code Location} 클래스는 콘서트 장소 정보를 나타내는 모델입니다.
 */
public class Location {
    private UUID locationID;
    private int coordinate_1;
    private int coordinate_2;
    private List<Integer> coordinates;
    private String locationName;

    public Location(UUID locationID, int coordinate_1, int coordinate_2, String locationName) {
        this.locationID = locationID;
        this.coordinate_1 = coordinate_1;
        this.coordinate_2 = coordinate_2;
        this.locationName = locationName;
    }


    public Location(UUID locationID, List<Integer> coordinates) {
        this.locationID = locationID;
        this.coordinates = coordinates;
    }

    public UUID getLocationID() {
        return locationID;
    }

    public void setLocationID(UUID locationID) {
        this.locationID = locationID;
    }

    public int getCoordinate_1() {
        return coordinate_1;
    }

    public void setCoordinate_1(int coordinate_1) {
        this.coordinate_1 = coordinate_1;
    }

    public int getCoordinate_2() {
        return coordinate_2;
    }

    public void setCoordinate_2(int coordinate_2) {
        this.coordinate_2 = coordinate_2;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public void setCoordinates(List<Integer> coordinates) {
        this.coordinates = coordinates;
    }

    public List<Integer> getCoordinates() {
        return coordinates;
    }
// TODO: 구현 필요
}
