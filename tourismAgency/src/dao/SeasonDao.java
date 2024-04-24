package dao;

import core.Db;
import entity.Pension;
import entity.Season;

import java.sql.*;
import java.util.ArrayList;

public class SeasonDao {
    private final Connection con;
    private Pension pension;

    public SeasonDao(){
        this.con = Db.getInstance();
    }

//    public ArrayList<Season> findSeasonByHotelId(int selectedHotelId){
//        ArrayList<Season> seasonList = new ArrayList<>();
//        String query = "SELECT * FROM public.seasons WHERE season_hotel_id = ?";
//        try{
//            PreparedStatement pr = this.con.prepareStatement(query);
//            pr.setInt(1,selectedHotelId);
//
//            ResultSet rs = pr.executeQuery();
//            while(rs.next()){
//                seasonList.add(this.match(rs));
//            }
//        }catch (SQLException e){
//            System.out.println(e.getMessage());
//        }
//        return seasonList;
//    }
    public boolean save(Season season){
        String query = "INSERT INTO public.seasons " +
                "(season_hotel_id, start_date, finish_date)" +
                " values (?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,season.getHotelId());
            pr.setDate(2, Date.valueOf(season.getStartDate()));
            pr.setDate(3, Date.valueOf(season.getFinishDate()));
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public Season findSeasonByHotelID(int seasonHotelId){
        Season obj = null;
        String query = "SELECT * FROM public.seasons WHERE season_hotel_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,seasonHotelId);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }
    public boolean update(Season season){
        String query = "UPDATE public.seasons SET season_hotel_id = ? , start_date = ? ," +
                " finish_date = ? WHERE season_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, season.getHotelId());;
            pr.setDate(2, Date.valueOf(season.getStartDate()));
            pr.setDate(3, Date.valueOf(season.getFinishDate()));
            pr.setInt(4,season.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public Season match(ResultSet rs) throws SQLException{
        Season obj = new Season();
        obj.setId(rs.getInt("season_id"));
        obj.setHotelId(rs.getInt("season_hotel_id"));
        obj.setStartDate(rs.getDate("start_date").toLocalDate());
        obj.setFinishDate((rs.getDate("finish_date")).toLocalDate());
        return obj;
    }

}
