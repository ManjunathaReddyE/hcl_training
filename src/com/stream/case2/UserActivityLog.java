package com.stream.case2;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserActivityLog {

	public static void main(String[] args) {

		        final int MINS_IN_DAY = 1440;
		        final int MINS_IN_HOUR = 60;
		        Scanner in = new Scanner(System.in);
		        
		        
		        System.out.print("Enter Number of Users: ");
		        int n = in.nextInt();
		        in.nextLine();

		        if (n > 100 || n < 1) {
		            System.out.println("Invalid Input!");
		            System.out.println("No of users must be between 1 and 100");
		            return;
		        }

		        String records[][] = new String[n][6];

		        int monthDays[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

		        for (int i = 0; i < n; i++) {
		            System.out.println("Enter record of user " + (i+1) + ": ");
		            System.out.print("Enter User Identification: ");
		            records[i][0] = in.nextLine();
		            System.out.print("Enter Login Time(hh:mm): ");
		            records[i][1] = in.nextLine();
		            System.out.print("Enter Login Date(dd-mm): ");
		            records[i][2] = in.nextLine();
		            System.out.print("Enter Logout Time(hh:mm): ");
		            records[i][3] = in.nextLine();
		            System.out.print("Enter Logout Date(dd-mm): ");
		            records[i][4] = in.nextLine();
		        }
		        System.out.println();

		        
		        /* To calculate the user loged in time i'e duration of loggin hours of the user  */
		        int longIdx = 0;
		        int longDuration = 0;
		        
		        //kidsL.stream().mapToInt(a -> Arrays.stream(a).flatMapToInt(Arrays::stream).sum()).min().ifPresent(System.err::println);
		        LocalDate start = LocalDate.now();
		        LocalDate end = LocalDate.now().plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
		        List<LocalDate> dates = Stream.iterate(start, date -> date.plusDays(1)).limit(ChronoUnit.DAYS.between(start, end)).collect(Collectors.toList());
		        
		        LocalDate maxDate = dates.stream().max( Comparator.comparing( LocalDate::toEpochDay)).get();
		   
		        LocalDate minDate = dates.stream().min( Comparator.comparing( LocalDate::toEpochDay )).get();
		        
		        System.out.println("maxDate: "+maxDate+" minDate:  "+minDate);
		        
		        for (int i = 0; i < n; i++) {
		            int duration = 0;
		            int tempIdx = records[i][1].indexOf(':');
		            int loginHr = Integer.parseInt(records[i][1].substring(0, tempIdx));
		            int loginMin = Integer.parseInt(records[i][1].substring(tempIdx + 1));
		            tempIdx = records[i][3].indexOf(':');
		            int logoutHr = Integer.parseInt(records[i][3].substring(0, tempIdx));
		            int logoutMin = Integer.parseInt(records[i][3].substring(tempIdx + 1));
		            int m1 = loginHr * MINS_IN_HOUR + loginMin;
		            int m2 = logoutHr * MINS_IN_HOUR + logoutMin;

		            //If login & logout is on the same day
		            if (records[i][2].equals(records[i][4])) {
		                duration = m2 - m1;
		            }
		            else {
		                int daysDiff = 0;
		                tempIdx = records[i][2].indexOf('-');
		                int loginDay = Integer.parseInt(records[i][2].substring(0, tempIdx));
		                int loginMonth = Integer.parseInt(records[i][2].substring(tempIdx + 1));
		                tempIdx = records[i][4].indexOf('-');
		                int logoutDay = Integer.parseInt(records[i][4].substring(0, tempIdx));
		                int logoutMonth = Integer.parseInt(records[i][4].substring(tempIdx + 1));
		                
		                //If login & logout is in the same month
		                if (loginMonth == logoutMonth) {
		                    daysDiff = logoutDay - loginDay - 1;
		                }
		                else {
		                    daysDiff = monthDays[loginMonth - 1] - loginDay + logoutDay - 1;
		                }

		                duration = (MINS_IN_DAY - m1) + m2 + daysDiff * MINS_IN_DAY;
		            }

		            if (duration > longDuration) {
		                longDuration = duration;
		                longIdx = i;
		            }

		            int durHr = duration / 60;
		            int durMin = duration % 60;
		            records[i][5] = (durHr == 0 ? "00" : durHr)  
		            + ":" 
		            + (durMin == 0 ? "00" : durMin);
		        }

		        //System.out.println("maxDate: "+maxDate+" minDate:  "+minDate);
		        System.out.println("User\t\tLogin\t\tLogout\t\tDuration");
		        System.out.println("Identification\tTime & Date\tTime & Date\tHours:Minutes");
		        
		        for (int i = 0; i < n; i++) {
		            for (int j = 0; j < 6; j++) {
		                System.out.print(records[i][j] + "\t");
		            }
		            System.out.println();
		        }

		        System.out.println();
		        System.out.println("The user who logged in for longest duration:");
		        for (int j = 0; j < 6; j++) {
		            System.out.print(records[longIdx][j] + "\t");
		        }
		    }
		

}

