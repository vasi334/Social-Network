package com.example.lab4try.service;

public class ServicePrincipal<ID> {

    public ServiceNetwork serviceNetwork;

    public ServiceUser serviceUser;

    public ServicePrincipal(ServiceNetwork serviceNetwork, ServiceUser serviceUser) {
        this.serviceNetwork = serviceNetwork;
        this.serviceUser = serviceUser;
    }

    public ServiceNetwork getServiceNetwork() {
        return serviceNetwork;
    }

    public void setServiceNetwork(ServiceNetwork serviceNetwork) {
        this.serviceNetwork = serviceNetwork;
    }

    public ServiceUser getServiceUser() {
        return serviceUser;
    }

    public void setServiceUser(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }
}
