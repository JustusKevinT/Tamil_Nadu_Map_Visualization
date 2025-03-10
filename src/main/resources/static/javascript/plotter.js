document.addEventListener("DOMContentLoaded", function() {

    // Get saved map state from sessionStorage
    var savedZoom = sessionStorage.getItem('mapZoom');
    var savedCenter = sessionStorage.getItem('mapCenter');
    var initialZoom = savedZoom ? parseInt(savedZoom) : 6;
    var initialCenter = savedCenter ? JSON.parse(savedCenter) : [11.1271, 78.6569];

    // Initialize the map with the saved state or default values
    var map = L.map('map').setView(initialCenter, initialZoom);

    // Add a tile layer to the map
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
    }).addTo(map);

    // Fetch GeoJSON data
    fetch('/api/getMapData')
        .then(response => response.json())
        .then(geoJsonData => {
            // Fetch MySQL data
            fetch('/api/getData')
                .then(response => response.json())
                .then(hospitalData => {
                    // Create a marker cluster group
                    var markers = L.markerClusterGroup();

                    // Iterate over each feature in the GeoJSON file
                    L.geoJSON(geoJsonData, {
                        onEachFeature: function (feature, layer) {
                            var centroid;
                            if (feature.geometry.type === 'Polygon' || feature.geometry.type === 'MultiPolygon') {
                                centroid = turf.centroid(feature);
                            }

                            if (centroid) {
                                var coords = centroid.geometry.coordinates;
                                var hospital = hospitalData.find(h => h.hospitalName === feature.properties["Hospital Name"]);
                                var marker = L.marker([coords[1], coords[0]]);
                                var tooltipContent;

                                if (hospital) {
                                    tooltipContent = `<b>${feature.properties["Hospital Name"]}</b><br>`;
                                    tooltipContent += `Births: ${hospital.birth}<br>`;
                                    tooltipContent += `Deaths: ${hospital.death}<br>`;
                                } else if (feature.properties["Ward Name"] !== undefined) {
                                    tooltipContent = `<b>${feature.properties["Ward Name"]}</b><br>`;
                                } else if (feature.properties["Zone Name"] !== undefined) {
                                    tooltipContent = `<b>${feature.properties["Zone Name"]}</b><br>`;
                                } else if (feature.properties["NAME_2"] !== undefined) {
                                    tooltipContent = `<b>${feature.properties["NAME_2"]}</b><br>`;
                                } else {
                                    tooltipContent = `<b>Unknown</b><br>`;
                                }

                                marker.bindTooltip(tooltipContent);
                                markers.addLayer(marker);
                            }
                        }
                    });

                    // Clear existing markers and add the new marker cluster group to the map
                    if (window.hospitalLayerGroup) {
                        map.removeLayer(window.hospitalLayerGroup);
                    }
                    map.addLayer(markers);
                    window.hospitalLayerGroup = markers;
                })
                .catch(error => console.error('Error loading MySQL data:', error));
        })
        .catch(error => console.error('Error loading the GeoJSON file:', error));

    // Function to save map state
    function saveMapState() {
        sessionStorage.setItem('mapZoom', map.getZoom());
        sessionStorage.setItem('mapCenter', JSON.stringify(map.getCenter()));
    }

    // Set interval to save map state and reload the page every 60 seconds
    setInterval(function() {
        saveMapState();
        location.reload();
    }, 60000);
});
