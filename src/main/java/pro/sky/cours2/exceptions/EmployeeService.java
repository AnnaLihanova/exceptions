package pro.sky.cours2.exceptions;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;


@Service
public class EmployeeService {
    static Employee[] employees = {
            new Employee("Ivanov", "Ivan"),
            null,
            new Employee("Шариков", "Роман"),
            new Employee("Дудник", "Петр"),
            new Employee("Кромкина", "Мария"),
            new Employee("Тарасова", "Татьяна"),
    };

    public Employee addEmployee(String lastName, String firstName) {
        Employee newEmployee = new Employee(lastName, firstName);
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getLastName().equals(lastName) && employees[i].getFirstName().equals(firstName)) {
                throw new EmployeeAlreadyAddedException("Employee already added");
            }
        }
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = newEmployee;
                return newEmployee;
            }
        }
        throw new ArrayOverflowException("Employee can't be added. Array is full");
    }

    public Employee removeEmployee(String lastName, String firstName) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getLastName().equals(lastName) && employees[i].getFirstName().equals(firstName)) {
                employees[i] = null;
                return null;
            }
        }
        throw new EmployeeNotFoundException("Employee not found");
    }

    public Employee findEmployee(String lastName, String firstName) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getLastName().equals(lastName) && employees[i].getFirstName().equals(firstName)) {
                return employees[i];
            }
        }
        throw new EmployeeNotFoundException("Employee not found");
    }
}

