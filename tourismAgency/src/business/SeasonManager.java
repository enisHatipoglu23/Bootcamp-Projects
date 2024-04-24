package business;

import core.Helper;
import dao.SeasonDao;
import entity.Pension;
import entity.Season;

import java.util.ArrayList;

public class SeasonManager {
    private final SeasonDao seasonDao;
    public SeasonManager(){
        this.seasonDao = new SeasonDao();
    }
//    public ArrayList<Season> findSeasonByHotelId(int hotelId){
//        return this.seasonDao.findSeasonByHotelId(hotelId);
//    }
    public boolean save(Season season){
        return this.seasonDao.save(season);
    }
    public Season findSeasonByHotelID(int seasonHotelId){
        return this.seasonDao.findSeasonByHotelID(seasonHotelId);
    }

}
