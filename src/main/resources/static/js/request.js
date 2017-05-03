function getAllCars() {
    $.getJSON("/json/car/all").done(function (cars) {
        console.log("cars: ", cars);
    });
}

function getAllCustomers() {
    $.getJSON("/json/customer/all").done(function (customers) {
        console.log("customers", customers);
    })
}

function getAllCarsModel() {
    $.getJSON("/json/car/model/all").done(function (carModels) {
        console.log(carModels);
    })
}

function getAllCarsMake() {
    $.getJSON("/json/car/make/all").done(function (carMake) {
        console.log(carMake);
    })
}

function getValueFromModelInputField() {
    $("#carModel").keyup(function () {
        let carModel = this.value;
        let make = $('#carMake').val();
        $.getJSON("/json/car/" + make + "/model/" + carModel).done(function (carModels) {
            $('#carModels').empty();
            $.each(carModels, function (index, model) {
                $('#carModels').append('<p>' + model.model + '</p>');
            });
        })
    });
}

function getValueFromMakeInputField() {
    $("#carMake").keyup(function () {
        let carMake = this.value;
        $.getJSON("/json/car/make/" + carMake).done(function (carMakes) {
            $('#carMakes').empty();
            $.each(carMakes, function (index, make) {
                $('#carMakes').append('<p>' + make.make + '</p>').unique('#carMakes');
            });
        })
    });
}

function getCarFromDbOnSearchPage() {
    $("#carSearch").keyup(function () {
        let registrationNumber = this.value;
        $.getJSON("/json/car/search/" + registrationNumber).done(function (cars) {
            $('#cars').empty();
            $.each(cars, function (index, car) {
                console.log(car);
                $('#cars')
                    .append($('<a></a>').attr("href", "/car/edit/" + car.id)
                        .append($('<p>' + car.carRegistrationNumber + '</p>')));
            });
        })
    });
}

$(function ($) {
    let url = window.location.pathname;

    if (url == "/car/all") {
        getAllCars();
    }

    if (url == "/car/add") {
        getValueFromModelInputField();
        getValueFromMakeInputField();
    }

    if (url == '/car/edit') {
        getCarFromDbOnSearchPage();
    }


    // getAllCustomers();
});