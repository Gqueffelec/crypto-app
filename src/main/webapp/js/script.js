var updateId;
var removeId;
$(document).ready(function () {
    update();
    $("#showlist").hide();
    $("#create").on("click", function () {
        let cryptoName = $("#add").find(".name").val();
        let cryptoLabel = $("#add").find(".label").val();
        let cryptoActualPrice = $("#add").find(".actualprice").val();
        jQuery.get("CRUDCurrency", {
            crypto: "create",
            name: cryptoName,
            label: cryptoLabel,
            actualprice: cryptoActualPrice
        }).done(function (msg) {
            alert(msg);
            $("#add").modal("hide");
            update();
        })
    });
    $("#remove").on("click", function () {
        let cryptoId = $("#rmv").find(".id").val();
        jQuery.get("CRUDCurrency", {
            crypto: "remove",
            id: cryptoId
        }).done(function (msg) {
            $("#rmv").modal("hide");
            alert(msg);
            update();
        })
    });
    $("#updatebutton").on("click", function () {
        $("#updatedata").hide();
        $("#idselector").show();
    });
    $("#update").on("click", function () {
        let cryptoName = $("#upd").find(".name").val();
        let cryptoLabel = $("#upd").find(".label").val();
        let cryptoActualPrice = $("#upd").find(".actualprice").val();
        let cryptoId = $("#upd").find(".id").val();
        jQuery.get("CRUDCurrency", {
            crypto: "update",
            id: cryptoId,
            name: cryptoName,
            label: cryptoLabel,
            actualprice: cryptoActualPrice
        }).done(function (msg) {
            alert(msg);
            $("#upd").modal("hide");
            update();
        })
    });
    $("#selectId").on('click', function () {
        let cryptoId = $("#upd").find(".id").val();
        jQuery.get("CRUDCurrency", {
            crypto: "getdata",
            id: cryptoId
        }).done(function (json) {
            $("#updatedata").find(".name").val(json.name);
            $("#updatedata").find(".label").val(json.label);
            $("#updatedata").find(".actualprice").val(json.actualPrice);
            $("#updatedata").show();
            $("#idselector").hide();
            $("#update").removeAttr("disabled");
        })
    })
    $("#list").on('click', function () {
        if ($("#showlist").is(":hidden")) {
            $("#showlist").show();
        } else {
            $("#showlist").hide();
        }
    })
    $("#newAssetButton").on("click", function () {
        $("#idselectorAsset").show();
        $("#createAssetData").hide();
    })
    $("#selectIdAsset").on('click', function () {
        $("#createAssetData").show();
        $("#idselectorAsset").hide();
        $("#createAsset").removeAttr("disabled");
    })
    $("#createAsset").on("click", function () {
        let assetId = $("#newAsset").find(".id").val();
        let assetNumber = $("#newAsset").find(".numberofaction").val();
        jQuery.get("Assets", {
            asset: "add",
            id: assetId,
            numberofaction: assetNumber,
        }).done(function (msg) {
            alert(msg);
            $("#add").modal("hide");
            update();
        })
    })
    $("#updateAssetData .numberofaction").on("change", function () {
        let value = parseInt($(this).val());
        if (value >= 0) {
            console.log(value);
            $("#updateAssetButton").removeAttr("disabled");
        } else {
            $("#updateAssetButton").attr("disabled", true);
        }
    })
    $("#updateAssetButton").on('click', function () {
        let updateAssetNumber = $("#updateAssetData .numberofaction").val();
        if (updateAssetNumber < 0) {
            return;
        }
        console.log(updateAssetNumber);
        jQuery.get("Assets", {
            asset: "update",
            id: updateId,
            numberofaction: updateAssetNumber
        }).done(function (msg) {
            alert(msg);
            $("#updateAsset").modal("hide");
            update();
        })
    })
    $("#removeCheck .yes").on('change', function () {
        console.log($(this).prop("checked"));
        if ($(this).prop("checked")) {
            $("#removeAssetButton").removeAttr("disabled");
        } else {
            $("#removeAssetButton").attr("disabled", true);
        }
    })
    $("#removeAssetButton").on('click', function () {

        jQuery.get("Assets", {
            asset: "remove",
            id: removeId
        }).done(function (msg) {
            alert(msg);
            $("#removeAsset").modal("hide");
            update();
            console.log("remove done");
        })
    })
});

$(window).on('load', function () {
    updateButton();
})

function updateButton() {
    $.each($(".updateAsset"), function () {
        $(this).off('click');
        $(this).on('click', function () {
            $("#updateAssetData .numberofaction").val("");
            updateId = parseInt(($(this).attr('id')).charAt(4));
        })
    })
    $.each($(".removeAsset"), function () {
        $(this).off('click');
        $(this).on('click', function () {
            $("#removeCheck .yes").prop("checked", false);
            removeId = parseInt(($(this).attr('id')).charAt(6));
            console.log(removeId);
        })
    })
}

function update() {
    updateData();
    refresh();
    updateNewAsset();
    updateAssetsList();
}

function updateAssetsList() {
    jQuery.get("list", {
        list: "refresh"
    }).done(function (assetList) {
        let table = $("#assetslisting");
        table.empty();
        console.log(assetList);
        $.each(assetList, function (id, assets) {
            table.append("<tr></tr>");
            let tr = $("#assetslisting > tr:last-child");
            tr.append("<td>" + assets[0] + "</td>");
            tr.append("<td>" + assets[1] + "</td>");
            tr.append("<td>" + assets[2] + "</td>");
            tr.append("<td>" + assets[3] + "</td>");
            tr.append("<td>" + assets[4] + "</td>");
            tr.append("<td><span class='updateAsset' id='edit" + assets[5] + "'><i class='fas fa-pen' data-toggle='modal' data-target='#updateAsset'> </i></span><span class='removeAsset' id='remove" + assets[5] + "'><i class='far fa-trash-alt' data-toggle='modal' data-target='#removeAsset'> </i></span></td>")
        })
        updateButton();
    })
}


function updateData() {
    jQuery.get("CRUDCurrency", {
        crypto: "list"
    }).done(function (list) {
        let select = $("#idselector select");
        $("#showlist").empty();
        $("#showlist").append("<div class='row'></div>");
        let div = $("#showlist div:first-child");
        div.append("<div class='col-xs-1-1'><h4>" + "Id" + "</h4></div>");
        div.append("<div class='col-xs-3-3'><h4>" + "Name" + "</h4></div>");
        div.append("<div class='col-xs-5-5'><h4>" + "Label" + "</h4></div>");
        div.append("<div class='col-xs-7-7'><h4>" + "Actual Price" + "</h4></div>");
        select.empty();
        $.each(list, function (id, currency) {
            select.append("<option value='" + currency.id + "'>" + currency.name + "</option>")
            $("#showlist > div > div:nth-child(1)").append("<p>" + currency.id + "</p>");
            $("#showlist > div > div:nth-child(2)").append("<p>" + currency.name + "</p>");
            $("#showlist > div > div:nth-child(3)").append("<p>" + currency.label + "</p>");
            $("#showlist > div > div:nth-child(4)").append("<p>" + currency.actualPrice + "</p>");
        });
    })
}

function refresh() {
    jQuery.get("navbar", {
        navbar: "refresh"
    }).done(function (data) {
        data = parseFloat(data);
        $("#totalAssets").removeClass().empty().text(data);
        if (data > 0) {
            $("#totalAssets").addClass("text-success");
        } else {
            $("#totalAssets").addClass("text-danger");
        }
    })
}

function updateNewAsset() {
    jQuery.get("navbar", {
        navbar: "list"
    }, function (list) {
        let select = $("#idselectorAsset select");
        select.empty()
        $.each(list, function (id, currency) {
            select.append("<option value='" + currency.id + "'>" + currency.name + "</option>");
        })
    })
}