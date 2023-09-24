package net.concheese.server.concert.model;

import java.util.UUID;

/**
 * {@code Location} 클래스는 콘서트 장소 정보를 나타내는 모델입니다.
 */
public class Location {
    private UUID locationID;
    private int coordinate_1;
    private int coordinate_2;
    private int coordinate_3;
    private String locationName;

    public Location(UUID locationID, int coordinate_1, int coordinate_2, int coordinate_3, String locationName) {
        this.locationID = locationID;
        this.coordinate_1 = coordinate_1;
        this.coordinate_2 = coordinate_2;
        this.coordinate_3 = coordinate_3;
        this.locationName = locationName;
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

    public int getCoordinate_3() {
        return coordinate_3;
    }

    public void setCoordinate_3(int coordinate_3) {
        this.coordinate_3 = coordinate_3;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
// TODO: 구현 필요
}
