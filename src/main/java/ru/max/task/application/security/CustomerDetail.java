package ru.max.task.application.security;

public interface CustomerDetail {
    ApplicationCustomer getCustomerByLogin(String login);
}
