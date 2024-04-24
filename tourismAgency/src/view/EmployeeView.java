package view;

import business.HotelManager;
import business.PensionManager;
import business.RoomManager;
import business.SeasonManager;
import core.Helper;
import entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class EmployeeView extends Layout{

    private User user;
    private JPanel container;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JTabbedPane tab_menu;
    private JPanel pnl_hotels;
    private JScrollPane scrl_hotels;
    private JTable tbl_hotels;
    private JPanel pnl_hotel_filter;
    private JTextField fld_hotel_name;
    private JButton btn_hotel_search;
    private JButton btn_clear;
    private JButton btn_add_new_hotel;
    private JLabel lbl_pension;
    private JPanel pnl_rooms;
    private JPanel pnl_reservation;
    private JScrollPane scrl_rooms;
    private JTable tbl_room;
    private JPanel pnl_room_filter;
    private JTextField fld_room_hotelname;
    private JTextField fld_room_checkin;
    private JTextField fld_room_checkout;
    private JTextField fld_room_adults;
    private JTextField fld_room_children;
    private JButton btn_room_search;
    private JTextField fld_city;
    private JPanel pnl_room;
    private JButton btn_pension;
    private JButton btn_add_season;
    private JComboBox cmb_room_hotelname;
    private JTextField fld_room_city;
    private JButton addRoomButton;
    private JComboBox cmb_room_address;
    private JPopupMenu popup_hotels = new JPopupMenu();
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();
    private DefaultTableModel tmdl_room = new DefaultTableModel();
    private HotelManager hotelManager;
    private Hotel hotel;

    private Room room;
    private RoomManager roomManager;
    private PensionManager pensionManager;
    private SeasonManager seasonManager;
    public EmployeeView(User user){
        this.seasonManager = new SeasonManager();
        this.pensionManager = new PensionManager();
        this.roomManager = new RoomManager();
        this.user = user;
        this.hotelManager = new HotelManager();
        this.add(container);
        this.guiInitilaze(1200,500, "Tourism Agency Management System - Personal Panel");
        this.lbl_welcome.setText("Welcome " + this.user.getUsername());
        loadUserButtons();
        loadHotelTable(null);

        loadUserPopupMenu();

        this.cmb_room_hotelname.setModel(new DefaultComboBoxModel<>());

        for (Hotel hotels : this.hotelManager.findAll()){
            this.cmb_room_hotelname.addItem(hotels.getName());
        }
        this.cmb_room_hotelname.setSelectedItem(null);


    }



    private void loadUserButtons(){
        btn_logout.addActionListener(e -> {
            dispose();
            LoginView loginView = new LoginView();
        });

        btn_add_new_hotel.addActionListener(e -> {
            HotelSaveView hotelSaveView = new HotelSaveView(new Hotel());
            hotelSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable(null);
                }
            });
        });

        this.btn_hotel_search.addActionListener(e -> {
            ArrayList<Hotel> filteredUsers = this.hotelManager.filter(
                    this.fld_city.getText().toUpperCase()
            );
            loadHotelTable(filteredUsers);
        });

        this.btn_clear.addActionListener(e -> {
            loadHotelTable(null);
            this.fld_hotel_name.setText(null);
            this.fld_city.setText(null);
        });
        this.btn_pension.addActionListener(e -> {
            PensionView pensionView = new PensionView(new Pension(), new Hotel());
        });
        btn_add_season.addActionListener(e -> {
            SeasonView seasonView = new SeasonView(new Season(),new Hotel());
        });

        btn_room_search.addActionListener(e -> {
            int selectedHotelIndex = this.cmb_room_hotelname.getSelectedIndex();
            ArrayList<Room> filteredRooms = this.roomManager.filter(
                    this.hotelManager.findAll().get(selectedHotelIndex).getId(),
                    this.fld_room_city.getText().toUpperCase()
            );
            loadRoomTable(filteredRooms);

        });
        addRoomButton.addActionListener(e -> {
            RoomSaveView roomSaveView = new RoomSaveView(new Room());
        });


    }
    private void loadUserPopupMenu() {
        this.tbl_hotels.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = tbl_hotels.rowAtPoint(e.getPoint());
                tbl_hotels.setRowSelectionInterval(selectedRow, selectedRow);
            }
        });

        this.popup_hotels.add("Sil").addActionListener(e -> {
            int selectId = Integer.parseInt(tbl_hotels.getValueAt(tbl_hotels.getSelectedRow(), 0).toString());
            if (Helper.confirm("sure")) {
                if (this.hotelManager.delete(selectId)) {

                    Helper.showMsg("done");
                    loadHotelTable(null);
                } else {
                    Helper.showMsg("error");
                }
            }

        });
        this.popup_hotels.add("Güncelle").addActionListener(e -> {
            int selectId = Integer.parseInt(tbl_hotels.getValueAt(tbl_hotels.getSelectedRow(),0).toString());
            HotelSaveView hotelSaveView = new HotelSaveView(this.hotelManager.getById(selectId));
            hotelSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable(null);
                }
            });
        });

        this.tbl_hotels.setComponentPopupMenu(this.popup_hotels);
    }

    private void loadHotelTable(ArrayList<Hotel> hotels){
        Object[] column = {"ID", "Name", "Address", "Mail", "Phone", "Star",
                "Car Park", "Wifi", "Pool", "Fitness", "Concierge", "Spa","Room service"};
        if(hotels == null){
            hotels = this.hotelManager.findAll();
        }
        DefaultTableModel clearModel = (DefaultTableModel) this.tbl_hotels.getModel();
        clearModel.setRowCount(0);
        this.tmdl_hotel.setColumnIdentifiers(column);
        for (Hotel hotel : hotels){
            Object[] obj = {hotel.getId(), hotel.getName(), hotel.getAddress(),hotel.getMail(),
            hotel.getPhone(), hotel.getStar(), Helper.handleBoolean(hotel.isCarPark()) ,Helper.handleBoolean(hotel.isWifi()) ,Helper.handleBoolean(hotel.isPool()) ,
            Helper.handleBoolean(hotel.isFitness()) ,Helper.handleBoolean(hotel.isConcierge()) ,Helper.handleBoolean(hotel.isSpa()) ,Helper.handleBoolean(hotel.isRoomService()) };
            this.tmdl_hotel.addRow(obj);
        }
        tbl_hotels.setModel(tmdl_hotel);
        tbl_hotels.getTableHeader().setReorderingAllowed(false);
        tbl_hotels.getColumnModel().getColumn(0).setMaxWidth(50);
        tbl_hotels.setEnabled(false);

    }

    private void loadRoomTable(ArrayList<Room> rooms){
        Object[] column = {"ID", "Hotel", "Address", "Pension", "Season Start","Season End", "Type", "Stock",
                "Adult Price", "Child Price", "Bed Capacity", "Square Meter", "Television", "Minibar","Game Console","Cash Box","Projection"};
        if(rooms == null){
            rooms = this.roomManager.findAll();
        }
        DefaultTableModel clearModel = (DefaultTableModel) this.tbl_room.getModel();
        clearModel.setRowCount(0);
        this.tmdl_room.setColumnIdentifiers(column);
        for (Room room : rooms){
            Object[] obj = {room.getId(), this.hotelManager.getById(room.getHotelId()).getName(),
                    this.hotelManager.getById(room.getHotelId()).getAddress(),
                    this.pensionManager.findPensionByHotelId(room.getHotelId()).get(0).getPensionType(),
                    this.seasonManager.findSeasonByHotelID(room.getHotelId()).getStartDate(),this.seasonManager.findSeasonByHotelID(room.getHotelId()).getFinishDate(),
                    room.getType(), room.getStock(), room.getAdultPrice(), room.getChildPrice(),
                    room.getBedCapacity(), room.getSquareMeter(), Helper.handleBoolean(room.isTelevision()), Helper.handleBoolean(room.isMinibar()) , Helper.handleBoolean(room.isGameConsole()) ,
                    Helper.handleBoolean(room.isCashBox()) , Helper.handleBoolean(room.isProjection()) };
            this.tmdl_room.addRow(obj);
        }
        tbl_room.setModel(tmdl_room);
        tbl_room.getTableHeader().setReorderingAllowed(false);
        tbl_room.getColumnModel().getColumn(0).setMaxWidth(50);
        tbl_room.setEnabled(false);

    }



}
