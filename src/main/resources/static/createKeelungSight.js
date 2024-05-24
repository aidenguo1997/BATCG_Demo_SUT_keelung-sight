const zoneData = [
    {zoneName: "七堵", zoneID: "qidu"},
    {zoneName: "中山", zoneID: "zhongshan"},
    {zoneName: "中正", zoneID: "zhongzheng"},
    {zoneName: "仁愛", zoneID: "ren-ai"},
    {zoneName: "安樂", zoneID: "anle"},
    {zoneName: "信義", zoneID: "sinyi"},
    {zoneName: "暖暖", zoneID: "nuannuan"}
]

function createZoneCard() {
    const accordion = document.getElementById("accordion");
    zoneData.forEach(i => {
        const div = document.createElement("div");
        div.className = "card";
        div.innerHTML = '<div class="card-header">' +
            '<a class="collapsed btn" data-bs-toggle="collapse" href="#' + i.zoneID + '">' +
            i.zoneName + '區' +
            '</a>' +
            '</div>' +
            '<div id=' + i.zoneID + ' class="collapse" data-bs-parent="#accordion">' +
            '<div class="card-body">' +
            '<div id="' + i.zoneID + 'card" class="row justify-content-center">' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>';
        accordion.appendChild(div);
    })
}

function createSightCard(photoURL, sightName, zone, category, description, address) {
    return '<div class="card" style="width:400px">' +
        '<img class="card-img-top" src="' + photoURL + '" alt="Card image" style="width:100%">' +
        '<div class="card-body">' +
        '<h4 class="card-title">' + sightName + '</h4>' +
        '<p class="card-text">' + zone + '</p>' +
        '<p class="card-text">' + category + '</p>' +
        '<p class="card-text">' + description + '</p>' +
        '<a href="https://www.google.com.tw/maps/place/' + address + '" target="_blank" class="btn btn-primary">' + address + '</a>' +
        '</div>' +
        '</div>';
}

function getSight() {
    zoneData.forEach(i => {
        let sightData = {};
        const $zoneCard = $("#" + i.zoneID + "card");
        $.get("/SightAPI?zone=" + i.zoneName, function (data) {
            console.log(data);
            sightData = data;
            load();
        });
        const load = () => {
            $.each(sightData, function (i, n) {
                if (n.photoURL === '') {
                    n.photoURL = "images/1661192135286.jpg";
                }
                $zoneCard.prepend(createSightCard(n.photoURL, n.sightName, n.zone, n.category, n.description, n.address))
            })
        }
    })
}

(function createKeelungSight() {
    createZoneCard();
    getSight();
})();
