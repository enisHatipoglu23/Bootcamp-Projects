package business;

import core.Helper;
import dao.RoomDao;
import dao.UserDao;
import entity.Role;
import entity.Room;
import entity.User;

import java.util.ArrayList;

public class RoomManager {
    private final RoomDao roomDao;

    public RoomManager(){
        this.roomDao = new RoomDao();
    }

    public ArrayList<Room> findAll(){
        return roomDao.findAll();
    }


    public boolean save(Room room){
        return this.roomDao.save(room);
    }

    public ArrayList<Room> filter(Integer hotelId, String hotelAddress){
        String query = "SELECT * FROM public.rooms r JOIN public.hotels h ON r.room_hotel_id = h.hotel_id";
        ArrayList<String> whereList = new ArrayList<>();

        // Otel kimliğine göre filtreleme
        if (hotelId != null){
            whereList.add("r.room_hotel_id = " + hotelId);
        }

        // Otel adresine göre filtreleme
        if (hotelAddress != null && !hotelAddress.isEmpty()){
            whereList.add("h.hotel_address LIKE '%" + hotelAddress + "%'");
        }

        if (!whereList.isEmpty()){
            String whereQuery = String.join(" AND ", whereList);
            query += " WHERE " + whereQuery;
        }

        return this.roomDao.query(query);

    }

}
