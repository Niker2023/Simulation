package main.java.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class ConsoleInterface implements Runnable {

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Введите размер мира, сначала количество строк, " +
                    "затем количество столбцов (пример: 10, Enter, 10, Enter):");
//            WorldMap.setRow(inputNumber(br));
//            WorldMap.setColumn(inputNumber(br));
            WorldMap.setRow(7);
            WorldMap.setColumn(7);
            System.out.println("Мир создан!");
            System.out.print("Для паузы либо продолжения нажмите Enter, для завершения симуляции введите любой символ.");
            while (br.readLine().isEmpty()) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (!Simulation.isPause()) {
                    System.out.print("Пауза, для продолжение нажмите Enter, для завершения симуляции введите любой символ.");
                }
                Simulation.setPause(!Simulation.isPause());
            }
            System.out.println("Симуляция завершена!");
            Simulation.stopSimulation();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isPositiveNumeric(String line) {
        try {
            int number = Integer.parseInt(line);
            if (number > 0) {
                return true;
            } else {
                System.out.println("Введите положительное число!");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Введите число!");
            return false;
        }
    }

    private Integer inputNumber(BufferedReader bufferedReader) throws IOException {
        String inputLine = "";
        boolean isInputCorrect = false;
        while (!isInputCorrect) {
            inputLine = bufferedReader.readLine();
            isInputCorrect = isPositiveNumeric(inputLine);
        }
        return Integer.parseInt(inputLine);
    }
}
