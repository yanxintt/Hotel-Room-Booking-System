package BookingInformation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileUtils {
    public static List<Object[]> readTestDataFromFile(String filePath) throws IOException {
        List<Object[]> testData = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                boolean hasExclusiveReward = Boolean.parseBoolean(parts[0]);
                int numberOfRoom = Integer.parseInt(parts[1]);
                int[] totalRoomNo = new int[3];
                int[] er = new int[3];
                for (int i = 0; i < 3; i++) {
                    totalRoomNo[i] = Integer.parseInt(parts[2 + i]);
                    er[i] = Integer.parseInt(parts[5 + i]);
                }
                testData.add(new Object[]{hasExclusiveReward, numberOfRoom, totalRoomNo, er});
            }
        }
        return testData;
    }

    public List<Object[]> paramMemberBooking() {
        try {
            return FileUtils.readTestDataFromFile("D:/LEARNING/Y2S2/AsgmTestingFinal/Asgm/paramMemberBooking.txt");
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList(); // Return an empty list in case of an error
        }
    }
}
