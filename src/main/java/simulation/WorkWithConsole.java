package main.java.simulation;

import main.java.simulation.board.GenerateField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class WorkWithConsole implements Runnable {

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Введите размер мира, сначала количество строк, " +
                    "затем количество столбцов (пример: 10, Enter, 10, Enter):");
            GenerateField.setRow(Integer.parseInt(br.readLine()));
            GenerateField.setColumn(Integer.parseInt(br.readLine()));
            GenerateField.getMap();
            System.out.println("Мир создан!");
            System.out.println("Для паузы либо продолжения нажмите Enter, для завершения симуляции введите любой символ.");
            while (br.readLine().isEmpty()) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (!Simulation.isPause()) {
                    System.out.println("Пауза, для продолжение нажмите Enter, для завершения симуляции введите любой символ.");
                }
                Simulation.setPause(!Simulation.isPause());
            }
            System.out.println("Симуляция завершена!");
            Simulation.stopSimulation();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
