import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GoogleMapHTMLContent {
	public static void main(String[] args) {
		HTMLMapContent("11355", "Testing");
	}

	public static void HTMLMapContent(String zipCode, String doctor) {
		String originalContentPath = "src/OriginalHTML.html";

		String newContentPath = "src/GoogleMap.html";
		
		
		double lat = 0.0;
		double lon = 0.0;
		// convert user's zip code to latitude and longitude
		try {
			double[] coordinates = ZipCodeConverter.getLatLong(zipCode);
			lat = coordinates[0];
			lon = coordinates[1];

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String content = "";
		try {
			// Read the content of the HTML file into a StringBuilder
			StringBuilder contentBuilder = new StringBuilder();
			try (BufferedReader br = new BufferedReader(new FileReader(originalContentPath))) {
				String line;
				while ((line = br.readLine()) != null) {
					contentBuilder.append(line).append("\n");
				}
			}
			// Replace the old content with the new content
			content = contentBuilder.toString();

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Convert the source code from a HTML file to a string
		String htmlContent = content;

		// Replace latitude and longitude to correspond user zip code
		String newLocation = htmlContent.replace("        const location = {lat: 40.748817, lng: -73.985428}; ",
				"        const location = {lat: " + lat + ", lng: " + lon + "}; ");

		// Then replace the search value with desired input
		String newHTML = newLocation.replace(
				"<input type=\"text\" id=\"search-box\" placeholder=\"Search for places\" value=\"cardiologist\">",
				"<input type=\"text\" id=\"search-box\" placeholder=\"Search for places\" value=\"" + doctor + "\">");

		replaceHTMLContent(newContentPath, newHTML);
	}

	public static void replaceHTMLContent(String filePath, String newContent) {
		try {
			// Write the new content to the file
			FileWriter writer = new FileWriter(filePath);
			writer.write(newContent);
			writer.close();
			System.out.println("HTML content replaced successfully.");
		} catch (IOException e) {
			System.err.println("Error while replacing HTML content: " + e.getMessage());
		}
	}
	
	public static void HTMLMapOriginal() {
		String localpath = "src/GoogleMap.html";
		String originalHTML = "<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Google Maps with Search Box, Nearby Search, and Place Details</title>\r\n"
				+ "    <style>\r\n"
				+ "        #map {\r\n"
				+ "            height: 800px;\r\n"
				+ "            width: 100%;\r\n"
				+ "            margin-bottom: 10px;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        #search-box {\r\n"
				+ "            width: 300px;\r\n"
				+ "            margin-bottom: 10px;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        #place-details {\r\n"
				+ "            margin-top: 10px;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        button {\r\n"
				+ "            margin-right: 10px;\r\n"
				+ "        }\r\n"
				+ "    </style>\r\n"
				+ "    <script></script>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "\r\n"
				+ "<div id=\"map\"></div>\r\n"
				+ "<input type=\"text\" id=\"search-box\" placeholder=\"Search for places\" value=\"cardiologist\">\r\n"
				+ "<div id=\"place-details\"></div>\r\n"
				+ "<button onclick=\"clearMarkers()\">Clear Markers</button>\r\n"
				+ "<!--<button onclick=\"refreshPage()\">Refresh Page</button>-->\r\n"
				+ "\r\n"
				+ "<script>\r\n"
				+ "    let map;\r\n"
				+ "    let service;\r\n"
				+ "    let infowindow;\r\n"
				+ "    let customMarkers = [];\r\n"
				+ "\r\n"
				+ "    function initMap() {\r\n"
				+ "        const location = {lat: 40.748817, lng: -73.985428}; // Your desired location coordinates\r\n"
				+ "\r\n"
				+ "        map = new google.maps.Map(document.getElementById(\"map\"), {\r\n"
				+ "            center: location,\r\n"
				+ "            zoom: 15,\r\n"
				+ "        });\r\n"
				+ "\r\n"
				+ "        service = new google.maps.places.PlacesService(map);\r\n"
				+ "        infowindow = new google.maps.InfoWindow();\r\n"
				+ "\r\n"
				+ "        // Create a search box and link it to the UI element\r\n"
				+ "        const searchBox = new google.maps.places.SearchBox(\r\n"
				+ "            document.getElementById(\"search-box\")\r\n"
				+ "        );\r\n"
				+ "\r\n"
				+ "        // Bias the SearchBox results towards the map's viewport\r\n"
				+ "        map.addListener(\"bounds_changed\", () => {\r\n"
				+ "            searchBox.setBounds(map.getBounds());\r\n"
				+ "        });\r\n"
				+ "\r\n"
				+ "        // Listen for the event when a user selects a prediction from the search box\r\n"
				+ "        searchBox.addListener(\"places_changed\", () => {\r\n"
				+ "            const places = searchBox.getPlaces();\r\n"
				+ "\r\n"
				+ "            if (places.length === 0) {\r\n"
				+ "                return;\r\n"
				+ "            }\r\n"
				+ "\r\n"
				+ "            // Clear any existing markers\r\n"
				+ "            clearMarkers();\r\n"
				+ "\r\n"
				+ "            // For each place, get the icon, name, and location\r\n"
				+ "            const bounds = new google.maps.LatLngBounds();\r\n"
				+ "\r\n"
				+ "            places.forEach((place) => {\r\n"
				+ "                if (!place.geometry) {\r\n"
				+ "                    console.log(\"Returned place contains no geometry\");\r\n"
				+ "                    return;\r\n"
				+ "                }\r\n"
				+ "\r\n"
				+ "                // Create a custom marker for each place\r\n"
				+ "                const marker = new google.maps.Marker({\r\n"
				+ "                    map,\r\n"
				+ "                    title: place.name,\r\n"
				+ "                    position: place.geometry.location,\r\n"
				+ "                });\r\n"
				+ "\r\n"
				+ "                google.maps.event.addListener(marker, \"click\", () => {\r\n"
				+ "                    // Retrieve and display place details\r\n"
				+ "                    displayPlaceDetails(place);\r\n"
				+ "                });\r\n"
				+ "\r\n"
				+ "                bounds.extend(place.geometry.location);\r\n"
				+ "                customMarkers.push(marker);\r\n"
				+ "            });\r\n"
				+ "\r\n"
				+ "            map.fitBounds(bounds);\r\n"
				+ "        });\r\n"
				+ "\r\n"
				+ "        // Perform a nearby search\r\n"
				+ "        const request = {\r\n"
				+ "            location: location,\r\n"
				+ "            radius: 1000, // Search within a 1km radius\r\n"
				+ "            type: [\"\"], // Change to your desired type (e.g., \"hospital\")\r\n"
				+ "        };\r\n"
				+ "\r\n"
				+ "    }\r\n"
				+ "\r\n"
				+ "    function createMarker(place) {\r\n"
				+ "        const marker = new google.maps.Marker({\r\n"
				+ "            map: map,\r\n"
				+ "            position: place.geometry.location,\r\n"
				+ "        });\r\n"
				+ "\r\n"
				+ "        google.maps.event.addListener(marker, \"click\", () => {\r\n"
				+ "            // Retrieve and display place details\r\n"
				+ "            displayPlaceDetails(place);\r\n"
				+ "        });\r\n"
				+ "\r\n"
				+ "        customMarkers.push(marker);\r\n"
				+ "    }\r\n"
				+ "\r\n"
				+ "    function clearMarkers() {\r\n"
				+ "        customMarkers.forEach((marker) => {\r\n"
				+ "            marker.setMap(null);\r\n"
				+ "        });\r\n"
				+ "        customMarkers = [];\r\n"
				+ "    }\r\n"
				+ "\r\n"
				+ "    function displayPlaceDetails(place) {\r\n"
				+ "        const placeDetails = document.getElementById(\"place-details\");\r\n"
				+ "        placeDetails.innerHTML = `<strong>${place.name}</strong><br>${place.formatted_address}<br>Rating: ${place.rating}`;\r\n"
				+ "    }\r\n"
				+ "\r\n"
				+ "    function refreshPage() {\r\n"
				+ "        location.reload();\r\n"
				+ "    }\r\n"
				+ "</script>\r\n"
				+ "\r\n"
				+ "<script>\r\n"
				+ "    // Replace 'YOUR_API_KEY' with your actual Google Maps API key.\r\n"
				+ "    function loadScript() {\r\n"
				+ "        const script = document.createElement(\"script\");\r\n"
				+ "        script.src = `https://maps.googleapis.com/maps/api/js?key=AIzaSyAmNkaGHturLmgMk_KAxICazFBh38Zoov8&libraries=places&callback=initMap`;\r\n"
				+ "        script.async = true;\r\n"
				+ "        document.head.appendChild(script);\r\n"
				+ "    }\r\n"
				+ "    loadScript();\r\n"
				+ "</script>\r\n"
				+ "\r\n"
				+ "</body>\r\n"
				+ "</html>\r\n";
		replaceHTMLContent(localpath, originalHTML);

	}
}

