package view;

import business.HotelManager;
import business.PensionManager;
import entity.Hotel;
import entity.Room;

import javax.swing.*;

public class RoomSaveView extends Layout{
    private JPanel container;
    private JLabel fld_room;
    private JLabel fld_hotel_room;
    private JComboBox cmb_hotel_select;
    private JComboBox cmb_pension_select;
    private JComboBox cmb_season_select;
    private JComboBox cmb_select_room_type;
    private JTextField tfld_stock;
    private JTextField tfld_aduld_price;
    private JTextField tfld_child_price;
    private JRadioButton rdo_minibar;
    private JRadioButton rdo_tv;
    private JRadioButton rdo_gameconsole;
    private JRadioButton rdo_cashbox;
    private JRadioButton rdo_projection;
    private JButton btn_save;
    private JLabel fld_pension;
    private JLabel fld_season;
    private JLabel fld_room_type;
    private JLabel fld_stock;
    private JLabel fld_adult_price;
    private JLabel fld_child_price;
    private Room room;
    private Hotel hotel;
    private HotelManager hotelManager;
    private PensionManager pensionManager;
    public RoomSaveView(Room room){
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.room = room;
        this.add(container);
        this.guiInitilaze(650,500, "Add Room");

        this.loadComboboxFields();

    }
    private void loadComboboxFields(){
        this.cmb_hotel_select.setModel(new DefaultComboBoxModel<>());

        for (Hotel hotel : this.hotelManager.findAll()){
            this.cmb_hotel_select.addItem(hotel.getName());
        }
        int selectedHotelIndex = this.cmb_hotel_select.getSelectedIndex();
        // this.cmb_hotel_select.setSelectedItem(null);

        this.cmb_pension_select.setModel(new DefaultComboBoxModel<>());
        this.cmb_pension_select.addItem(this.pensionManager.findAll().get(selectedHotelIndex).getPensionType());
    }
}
