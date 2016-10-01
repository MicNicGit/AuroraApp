package com.example.michael.myapplication;

import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Michael on 09/22/16.
 */
public class auroraData {
    int[][] data = new int[512][];

    public auroraData(){}

    private int findAuroraProbablility( double latitude, double longitude){
        int lat = (int)((latitude + 90)*2.844444444);
        int lon = 360 - (int)(longitude * 0.3515625);
        return data[lat][lon];
    }

    private int[] stringArrayToIntArray(String[] values) {
        int[] answer = new int[values.length];
        for(int i = 0; i < values.length; ++i) {
            answer[i] = Integer.parseInt(values[i]);
        }
        return answer;
    }

    public void readData(BufferedReader reader) throws IOException {
        String line = reader.readLine();

        while(line.charAt(0) == '#') {
            line = reader.readLine();
        }

        for(int i = 0; i < 512; ++i)
        {
            line = line.trim();
            String[] parts = line.split("\\s+");
            data[i] = stringArrayToIntArray(parts);
            line = reader.readLine();
        }
    }

    public void setTextView(String latitude, String longitude, TextView AuroraTextView) {
        int prob = findAuroraProbablility(Double.parseDouble(latitude),Double.parseDouble(longitude));
        AuroraTextView.setText("Aurora Probability: " + prob + "%");
    }
}
