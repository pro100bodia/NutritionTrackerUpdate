function setData(val){
    var newClientData = {
        id: $("#client_id").val(),

        name: $("#name").val().substr(
                $("#name").val().indexOf(":") + 1),

        gender: $("#gender").val().substr(
                $("#gender").val().indexOf(":") + 1),

        height: $("#height").val().substr(
                $("#height").val().indexOf(":") + 1),

        weight: $("#weight").val().substr(
                $("#weight").val().indexOf(":") + 1),

        lifeStyle: $("#lifeStyle").val().substr(
                $("#lifeStyle").val().indexOf(":") + 1)
    };

    $.post(
        val,
        newClientData,
        function(result){
            console.log("Client data changed successfully");
        }
    );
}

var state = 1;

function enableInput(val){
    state *= -1;
    if(state == -1){
        $('.personal_data input').prop('disabled', false);
        $('#config_personal_data').html('<span class="glyphicon glyphicon-ok"></span>');
    }else{
        $('#config_personal_data').html('<span class="glyphicon glyphicon-cog"></span>');
        $('.personal_data input').prop('disabled', true);
        setData(val);
    }
}


function addToPlate(food, amount){

//    $.post(
//        "/fill_plate",
//        {
//            food_selection: food,
//            amount: amount
//        },
//        function(result){
//            alert(result);
//            $('.plate_table tbody').append(result);
//        }
//    );
var formData={
    food_id: $("#food_selection").val(),
    amount: $("#food_amount").val()
};
console.log(window.location);

 $.ajax({
            type: "GET",
            contentType: "application/json",
            url: window.origin + "/fill_plate",
            data: JSON.stringify(formData),
            dataType: "json",
            success: function(result){
                             if(result.status == "Done"){
                               $(".plate-table tbody").append(result);
                               console.log("Success: ", result);
                             }else{
                               console.log("Fail: ", result);
                             }
                           },
            error: function(e){
                console.log("ERROR: " + e.toString());

            }
    });
}

function caculateNorm(){
    $.post(
        "/nutrition_tracker/norm",
        function(result){
            $('#def_value').html(result);
            $('.plate_table tbody').text("");
        }
    );
}