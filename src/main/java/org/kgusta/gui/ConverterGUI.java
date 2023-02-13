package org.kgusta.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Objects;

/**
 * Конвертер валют с предустановлеными курсами некоторых валют,
 * по отношению к фунту стерлигов.
 * Эта связка дает возможность высчитывать курсы между собой.
 */

public class ConverterGUI {
    private String[] currencyUnits = {
            "units",
            "Australian Dollar",
            "Canadian Dollar",
            "Chinese Yuan",
            "Euro",
            "Pound Sterling",
            "Japanese Yen",
            "South Korean Won",
            "US Dollar"
    };

    private double Australian_Dollar = 1.92;
    private double Canadian_Dollar = 1.71;
    private double Chinese_Yuan = 9.1;
    private double Euro = 1.18;
    private double Pound_Sterling = 1.0;
    private double Japanese_Yen = 141.83;
    private double South_Korean_Won = 1531.17;
    private double US_Dollar = 1.29;

    private JFrame frame;
    private JComboBox firstCountry;
    private JTextField textField2;
    private JTextField textField1;
    private JComboBox secondCountry;
    private JButton convertButton;
    private JButton resetButton;
    private JPanel converter;
    private JLabel firstCurrencyUnit;
    private JLabel secondCurrencyUnit;
    private JLabel name;


    private ConverterGUI() {

        //###############################################
        //Ввод только цифр (не разобрался как прикрепить этот код
        // для проверки ввода только цифр)
//
//        textField1.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                char symbol = e.getKeyChar();
//
//                if (!Character.isDigit(symbol))
//                    return;
//                textField1.setText(textField1.getText() + symbol);
//            }
//        });
        //###############################################

        firstCountry.addItemListener(e -> {
            int position = firstCountry.getSelectedIndex();
            firstCurrencyUnit.setText(currencyUnits[position]);
        });

        secondCountry.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int position = secondCountry.getSelectedIndex();
                secondCurrencyUnit.setText(currencyUnits[position]);
            }
        });

        convertButton.addActionListener(new ActionListener() {

            //convert Currency
            @Override
            public void actionPerformed(ActionEvent e) {

                //Если не выбраны значения и нажата кнопка Convert, то появиться сообщение.
                if (firstCountry.getSelectedIndex() == 0 || secondCountry.getSelectedIndex() == 0
                        || textField1.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "You must select both countries and input the amount.", // То что выводим в сообщении
                            "Error message", //
                            JOptionPane.INFORMATION_MESSAGE); // Иконка Информации
                    return;
                }

                /*
                Конвертация ведется через расет коэффициета.
                У каждой переменной Australian_Dollar, Canadian_Dollar и т.д. записано ее отношение к
                Фунту стерлинга. Опираясь на это ниже произведен расчет одной валюты в другую.
                */

                double amountToChange = Double.parseDouble(textField1.getText());

                double amountInPounds = 0.0;

                switch (Objects.requireNonNull(firstCountry.getSelectedItem()).toString()) {
                    case "AUD":
                        amountInPounds = amountToChange / Australian_Dollar;
                        break;
                    case "CAD":
                        amountInPounds = amountToChange / Canadian_Dollar;
                        break;
                    case "CNY":
                        amountInPounds = amountToChange / Chinese_Yuan;
                        break;
                    case "EUR":
                        amountInPounds = amountToChange / Euro;
                        break;
                    case "GBP":
                        amountInPounds = amountToChange / Pound_Sterling;
                        break;
                    case "JPY":
                        amountInPounds = amountToChange / Japanese_Yen;
                        break;
                    case "KRW":
                        amountInPounds = amountToChange / South_Korean_Won;
                        break;
                    case "USD":
                        amountInPounds = amountToChange / US_Dollar;
                        break;
                    default:
                        amountInPounds = 0.0;
                }

                double amountChanged = 0.0;

                switch (Objects.requireNonNull(secondCountry.getSelectedItem()).toString()) {
                    case "AUD":
                        amountChanged = amountInPounds * Australian_Dollar;
                        break;
                    case "CAD":
                        amountChanged = amountInPounds * Canadian_Dollar;
                        break;
                    case "CNY":
                        amountChanged = amountInPounds * Chinese_Yuan;
                        break;
                    case "EUR":
                        amountChanged = amountInPounds * Euro;
                        break;
                    case "GBP":
                        amountChanged = amountInPounds * Pound_Sterling;
                        break;
                    case "JPY":
                        amountChanged = amountInPounds * Japanese_Yen;
                        break;
                    case "KRW":
                        amountChanged = amountInPounds * South_Korean_Won;
                        break;
                    case "USD":
                        amountChanged = amountInPounds * US_Dollar;
                        break;
                    default:
                        amountChanged = 0.0;
                }

                String value = String.format("%.2f", amountChanged);
                textField2.setText(value);
            }
        });


        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                firstCountry.setSelectedIndex(0);
                secondCountry.setSelectedIndex(0);
                textField1.setText("");
                textField2.setText("");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Converter");
        frame.setLayout(null);
        frame.setContentPane(new ConverterGUI().converter);
        //frame.setSize(1500,1000); //размер нашего окна
        frame.setVisible(true); //окно программы видимое
        frame.setLocationRelativeTo(null); //окно программы располагается в центре экрана
        frame.setResizable(false); //false - не можем менять размеры заданного окна, true - можем
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //закрывать программу полностью при нажатии на "Х"
        frame.pack();
    }
}
