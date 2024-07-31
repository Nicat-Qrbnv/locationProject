import { Loader } from '@googlemaps/js-api-loader';

let map;
let markers = [];

async function initMap() {
    const response = await fetch('/api/maps-key');
    const apiKey = await response.text();

    const loader = new Loader({
        apiKey: apiKey,
        version: "weekly",
        libraries: ["places", "marker", "journeySharing", "streetView", "visualization"]
    });

    map = await loader.load().then(() => {
        return new google.maps.Map(document.getElementById('map'), {
            center: { lat: 40.10789164639515, lng: 46.04158226806454 },
            zoom: 14,
            mapTypeId: 'terrain',
            tilt: 45,
            gestureHandling: "cooperative",
            heading: 90
        });
    });

    fetchMarkersAndDisplay();
}

async function fetchMarkersAndDisplay() {
    const response = await fetch('/api/v1/markers/all');
    const data = await response.json();

    data.forEach(marker => {
        const mapMarker = new google.maps.Marker({
            position: { lat: marker.latitude, lng: marker.longitude },
            map: map,
            title: marker.description
        });
        markers.push(mapMarker);
    });
}

function clearMarkers() {
    markers.forEach(marker => marker.setMap(null));
    markers = [];
}

initMap();
