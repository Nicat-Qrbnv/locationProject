let map;
let markers = [];

async function initMap() {
    const { Map } = await google.maps.importLibrary("maps");
    const { AdvancedMarkerElement } = await google.maps.importLibrary("marker");

    map = new Map(document.getElementById("map"), {
        center: { lat: 40.10789164639515, lng: 46.04158226806454 },
        zoom: 14,
        mapId: "4504f8b37365c3d0",
        mapTypeId: 'terrain',
        tilt: 45,
        gestureHandling: "cooperative",
        heading: 90
    });

    fetchMarkersAndDisplay(AdvancedMarkerElement);
}

async function fetchMarkersAndDisplay(AdvancedMarkerElement) {
    try {
        const response = await fetch('/api/v1/markers/all');
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.json();
        clearMarkers();
        data.forEach(marker => {
            const mapMarker = new AdvancedMarkerElement({
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
    markers.forEach(marker => marker.map = null);
    markers = [];
}

initMap();
