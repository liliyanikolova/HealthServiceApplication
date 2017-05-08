/**
 * Created by Administrator on 5/8/2017.
 */


$(function () {
    loadJSONSpecialties();
});

function loadJSONSpecialties() {
    $.ajax({
        type: 'GET',
        url: '/specialties',
        data: 'json',
        success: function (specialties) {
            loadDOMSpecialties(specialties);
        }
    });
}

function loadDOMSpecialties(specialties) {
    $.each(specialties, function (index, specialty) {
        var specialtyName = specialty.name;
        var specialtyId = specialty.id;
        $('#specialtiesDropDown')
            .append($('<option/>')
                .text(specialtyName)
                .attr('specialtyId', specialtyId));
    });

}

