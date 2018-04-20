package storage;

import control.Booking;
import model.Person;
import model.Trip;

import java.io.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 *Les inn dummy gögn og setur inn í gagnagrunn
 */
    public class CvsReader
    {
        public static boolean readInData()
        {

            BufferedReader br = null;
            BufferedReader br2 = null;
            try
            {
                //Reading the csv file ATH muna að breyta slóðinni!
               br = new BufferedReader(new FileReader(".//files/trips.csv"));
               br2 = new BufferedReader(new FileReader("./files/bookings.csv"));

                //Create List for holding trip objects
                List<Trip> tripList = new ArrayList<Trip>();
                List<Booking> bookingList = new ArrayList<Booking>();

                String line = "";
                //Read to skip the header
                br.readLine();
                br2.readLine();
                //Reading from the second line
                while ((line = br2.readLine()) !=null)
                {
                    String[] bookingDetails = line.split(",");
                    if(bookingDetails.length > 0 )
                    {
                        Person p = new Person(bookingDetails[3],bookingDetails[4],bookingDetails[5]);
                        Booking b = new Booking(p,Integer.parseInt(bookingDetails[2]),Long.parseLong(bookingDetails[6]));
                        bookingList.add(b);
                    }
                }

                while ((line = br.readLine()) != null)
                {
                    String[] tripDetails = line.split(";");


                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

                    LocalDate date1 = LocalDate.parse(tripDetails[8], formatter);
                    LocalDate date2 = LocalDate.parse(tripDetails[15], formatter);

                    Date endDate = java.sql.Date.valueOf(date1);
                    Date startDate = java.sql.Date.valueOf(date2);

                    if(tripDetails.length > 0 )
                    {
                        //Save the trip details in Trip object
                        Person p = new Person(tripDetails[11],tripDetails[12],tripDetails[13]);
                        Trip t = new Trip(tripDetails[10], startDate, endDate,
                                tripDetails[6], Double.parseDouble(tripDetails[14]), Integer.parseInt(tripDetails[7]),
                                 Boolean.parseBoolean(tripDetails[16]), Boolean.parseBoolean(tripDetails[3]),
                                Boolean.parseBoolean(tripDetails[2]), Boolean.parseBoolean(tripDetails[4]),p
                                ,tripDetails[9],Integer.parseInt(tripDetails[1]));
                        tripList.add(t);
                    }
                }

                //Bætum við ferðum
                for(Trip t : tripList)
                {
                    DBManager.addTrip(t);
                    System.out.println("Ferð bætt við");
                }

                //Bætum við bókunum
                for(Booking b : bookingList)
                {
                    DBManager.addBooking(b);
                    System.out.println("Bókun bætt við");
                }

            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
            finally
            {
                try
                {
                    br.close();
                }
                catch(IOException ie)
                {
                    System.out.println("Error occured while closing the BufferedReader");
                    ie.printStackTrace();
                }
                return true;
            }
        }
    }
