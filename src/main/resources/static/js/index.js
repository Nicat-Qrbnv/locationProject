import { Loader } from "@googlemaps/js-api-loader";

let map;
let markers = [];

const loader = new Loader({
    apiKey: "xxxx",
    version: "weekly",
    libraries: ["places", "marker", "journeySharing", "streetView", "visualization"]
});

async function initMap() {
    loader.load().then(() => {
        map = new google.maps.Map(document.getElementById("map"), {
            center: { lat: 40.10789164639515, lng: 46.04158226806454 },
            zoom: 14,
            mapTypeId: 'terrain',
            tilt: 45,
            gestureHandling: "cooperative",
            heading: 90
        });

        fetchMarkersAndDisplay();
    });
}

async function fetchMarkersAndDisplay() {
    try {
        const response = await fetch('/api/v1/markers/all');
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.json();
        clearMarkers();
        data.forEach(marker => {
            const mapMarker = new google.maps.Marker({
                position: { lat: marker.latitude, lng: marker.longitude },
                map: map,
                title: marker.description
            });
            markers.push(mapMarker);
        });
    } catch (error) {
        console.error('There was a problem with the fetch operation:', error);
    }
}

function clearMarkers() {
    markers.forEach(marker => marker.setMap(null));
    markers = [];
}

initMap();
