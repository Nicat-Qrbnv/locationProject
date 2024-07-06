let map;
let markers = [];
let isAdmin = true;

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 40.106451, lng: 46.032736},
        zoom: 14
    });

    map.addListener('click', function (event) {
        if (isAdmin) {
            addMarker(event.latLng);
        }
    });

    fetchMarkers();
}

function addMarker(location) {
    let marker = new google.maps.Marker({
        position: location,
        map: map
    });

    markers.push(marker);

    saveMarkerToDatabase(location);
}

function saveMarkerToDatabase(location) {
    fetch('/addMarker', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            lat: location.lat(),
            lng: location.lng()
        })
    })
        .then(response => response.json())
        .then(data => console.log('Success:', data))
        .catch((error) => console.error('Error:', error));
}

function fetchMarkers() {
    fetch('/getMarkers')
        .then(response => response.json())
        .then(data => {
            data.forEach(markerData => {
                let location = new google.maps.LatLng(markerData.lat, markerData.lng);
                addMarker(location);
            });
        })
        .catch((error) => console.error('Error:', error));
}
