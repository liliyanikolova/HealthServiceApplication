/**
 * Created by Administrator on 5/9/2017.
 */

$(function () {
    $('#searchBox').on('input', search);
    search();
});

function search() {
    var searchEgn = $('#searchBox').val();
    $('#patientsList').empty();
    $.ajax({
        type: 'GET',
        url: '/patients/searchAsync',
        data: {
            egn: searchEgn
        },
        contentType: 'application/json',
        success: function (patients) {
            $.each(patients, function (i, patient) {
//                    alert(patient.firstName);
                addPatientsDOM(patient);
            });
        }
    });
}

function addPatientsDOM(patient) {
    var id = patient.id;
    var egn = patient.egn
    var firstName = patient.firstName;
    var lastName = patient.lastName;
    $('#patientsList').append(
        $('<tr></tr>')
            .append(
                $('<td></td>')
                    .html(egn))
            .append(
                $('<td></td>')
                    .html(firstName))
            .append(
                $('<td></td>')
                    .html(lastName))
            .append(
                $('<td></td>')
                    .append(
                        $('<a></a>')
                            .addClass('btn btn-warning')
                            .attr('href', '/patients/edit/' + id)
                            .html("Редактирай")
                    )
            )
            .append(
                $('<td></td>')
                    .append(
                        $('<a></a>')
                            .addClass('btn btn-warning')
                            .attr('onClick', 'if(!confirm("Сигурни ли сте?")) {return false;}')
                            .attr('href', 'doctor/delete/patient/' + id)
                            .html("Изтрий")
                    )
            )
    );
}