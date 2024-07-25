let map;
let markers = [];

function loadGoogleMapsAPI() {
    fetch('/api/maps-key')
        .then(response => response.text())
        .then(apiKey => {
            const script = document.createElement('script');
            script.src = `https://maps.googleapis.com/maps/api/js?key=${apiKey}&callback=initMap`;
            script.async = true;
            script.defer = true;
            document.head.appendChild(script);
        })
        .catch(error => console.error('Error fetching API key:', error));
}


loadGoogleMapsAPI();

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 40.106451, lng: 46.032736},
        zoom: 14
    });

    fetchMarkersAndDisplay();
}

function fetchMarkersAndDisplay() {
    fetch('/api/v1/markers/all')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            clearMarkers();
            data.forEach(marker => {
                const mapMarker = new google.maps.Marker({
                    position: { lat: marker.latitude, lng: marker.longitude },
                    map: map,
                    title: marker.description
                });
                markers.push(mapMarker);
            });
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
}

function clearMarkers() {
    markers.forEach(marker => marker.setMap(null));
    markers = [];
}

window.initMap = initMap;
