package VTTP.FinalProj.models;

import jakarta.json.JsonObject;

public class TIHLocation {

    private String uuid;
    private String name;
    private Location location;
    private String description;
    private Address address;
    private Image[] images;
    private String dataset;
    private Review[] reviews;
    private Contact contact;
    private String nearestMrtStation;
    private String officialWebsite;
    private String officialEmail;
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Location getLocation() {
        return location;
    }
    public void setGeoCode(Location location) {
        this.location = location;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public Image[] getImages() {
        return images;
    }
    public void setImages(Image[] images) {
        this.images = images;
    }
    public String getDataset() {
        return dataset;
    }
    public void setDataset(String dataset) {
        this.dataset = dataset;
    }
    public Review[] getReviews() {
        return reviews;
    }
    public void setReviews(Review[] reviews) {
        this.reviews = reviews;
    }
    public Contact getContact() {
        return contact;
    }
    public void setContact(Contact contact) {
        this.contact = contact;
    }
    public String getNearestMrtStation() {
        return nearestMrtStation;
    }
    public void setNearestMrtStation(String nearestMrtStation) {
        this.nearestMrtStation = nearestMrtStation;
    }
    public String getOfficialWebsite() {
        return officialWebsite;
    }
    public void setOfficialWebsite(String officialWebsite) {
        this.officialWebsite = officialWebsite;
    }
    public String getOfficialEmail() {
        return officialEmail;
    }
    public void setOfficialEmail(String officialEmail) {
        this.officialEmail = officialEmail;
    }

    public static TIHLocation createLoc(JsonObject locJSON){
        TIHLocation tihLocation = new TIHLocation();
        tihLocation.uuid = locJSON.getString("uuid");
        tihLocation.name = locJSON.getString("name");
        tihLocation.location = Location.createLocation(locJSON.getJsonObject("location"));//ERROR
        System.out.println("ASD \n\n\n\n\n");
        tihLocation.description = locJSON.getString("description");
        tihLocation.address = Address.createAddress(locJSON.getJsonObject("address"));
        tihLocation.images = Image.createImages(locJSON.getJsonArray("images"));
        tihLocation.dataset = locJSON.getString("dataset");
        tihLocation.reviews = Review.createReviews(locJSON.getJsonArray("reviews"));
        tihLocation.contact = Contact.createContact(locJSON.getJsonObject("contact"));
        tihLocation.nearestMrtStation = locJSON.getString("nearestMrtStation");
        tihLocation.officialWebsite = locJSON.getString("officialWebsite");
        tihLocation.officialEmail = locJSON.getString("officialEmail");
        return tihLocation;
    }



    /* sample restaurant
     * {
    "status": {
        "code": 200,
        "name": "OK",
        "message": "Content retrieved successfully."
    },
    "data": [
        {
            "uuid": "005f884d31ef0134a05b1e1947f8a840657",
            "name": "Afterwit Mexican Taqueria",
            "type": "Restaurants",
            "tags": [
                "Food & Beverages",
                "Restaurants",
                "Kampong Gelam"
            ],
            "description": "Offering a variety of classic Mexican dishes and refreshing beverages with a twist to the everyday cafe menu.",
            "body": "Offering a variety of classic Mexican dishes and refreshing beverages with a twist to the everyday cafe menu.",
            "rating": 4.1,
            "location": {
                "latitude": 1.303474,
                "longitude": 103.859986
            },
            "address": {
                "block": "778",
                "streetName": "North Bridge Rd",
                "floorNumber": "",
                "unitNumber": "",
                "buildingName": "",
                "postalCode": "198746"
            },
            "thumbnails": [
                {
                    "uuid": "10130eeed9087714a9cb091c4410c010f3c",
                    "url": "",
                    "libraryUuid": "",
                    "primaryFileMediumUuid": ""
                }
            ],
            "images": [
                {
                    "uuid": "10130eeed9087714a9cb091c4410c010f3c",
                    "url": "",
                    "libraryUuid": "",
                    "primaryFileMediumUuid": ""
                }
            ],
            "videos": [],
            "documents": [],
            "cuisine": "International Cuisine",
            "source": "One Kampong Gelam",
            "metadata": {
                "updatedDate": "2021-01-19T21:06:40+08:00",
                "createdDate": "2021-01-19T21:06:40+08:00"
            },
            "categoryDescription": "Food & Beverages",
            "dataset": "food_beverages",
            "reviews": [
                {
                    "authorName": "Aqil Anarki",
                    "authorURL": "https://www.google.com/maps/contrib/107094351851125632555/reviews",
                    "language": "en",
                    "profilePhoto": "https://lh3.googleusercontent.com/a/AItbvmlayUSiT00wiUnnGYQr2vXCBrIH_4kCRy8V_IGP=s128-c0x00000000-cc-rp-mo",
                    "rating": 5,
                    "text": "Had an impromptu dinner here and I must say the ambience, food and service was splendid! Definitely will comeback again for another round of good time! Also, would like to compliment an excellent customer touch point by server Ms Nadrah. She greeted us, showed our table and took our orders. After the meal, she cleared our table and bids  goodbye. Such a lovely lady. Also, she looks pregnant and with all the heavy plates please be extra careful. Hope to see you again!",
                    "time": "2022-07-17T08:32:51+08:00"
                },
                {
                    "authorName": "Rabi'atul ‘Adawiyah",
                    "authorURL": "https://www.google.com/maps/contrib/102056556553771359686/reviews",
                    "language": "en",
                    "profilePhoto": "https://lh3.googleusercontent.com/a-/AFdZucrowmrNIaeu9YzJFm0wuZSIEzpSoCrbjhR8Xp3gRg=s128-c0x00000000-cc-rp-mo",
                    "rating": 5,
                    "text": "The NACHOS with beef was amazing! The cooler - Berry Mint Cooler was refreshing! Quesadillas (chicken) was fresh and flavourful as well. The tacos is very filling so make sure you get two! It’s sufficient! Had to takeaway and so I can have it for breakfast!",
                    "time": "2022-08-10T12:46:53+08:00"
                },
                {
                    "authorName": "Sanjey Chrysh",
                    "authorURL": "https://www.google.com/maps/contrib/102628940760634320917/reviews",
                    "language": "en",
                    "profilePhoto": "https://lh3.googleusercontent.com/a-/AFdZuco5ZPGFvXUIB7Y1w3qyqSESV_VFtGa-VACU7xbPmA=s128-c0x00000000-cc-rp-mo",
                    "rating": 5,
                    "text": "Food was amazing! Had the taco platter for 3 and it was very filling. The ambiance was spectacular and very chill. I would definitely recommend this place for those looking for Halal Mexican food. :)",
                    "time": "2022-08-20T07:43:14+08:00"
                },
                {
                    "authorName": "Hermione Granger",
                    "authorURL": "https://www.google.com/maps/contrib/116395879228891446343/reviews",
                    "language": "en",
                    "profilePhoto": "https://lh3.googleusercontent.com/a-/AFdZucrlFpt32tTp8pqPeSnF0TQhiEl1e1l6edq4aLpp4A=s128-c0x00000000-cc-rp-mo",
                    "rating": 5,
                    "text": "Been here twice since they improved their menu. Previous menu and food was not great... But the new menu and food is quite delicious! Great service and ambience as well.",
                    "time": "2022-09-02T09:51:53+08:00"
                },
                {
                    "authorName": "Aks Zahra",
                    "authorURL": "https://www.google.com/maps/contrib/107146095022261721333/reviews",
                    "language": "en",
                    "profilePhoto": "https://lh3.googleusercontent.com/a-/AFdZucqEEUfrI0K9rXhMO7FZ-_A1mNDorzPvKgCSNWUgSA=s128-c0x00000000-cc-rp-mo",
                    "rating": 5,
                    "text": "Had a wonderful experience. Came here multiple times and the food never disappoints me. Love the food and the portion size. Worth it. Great service by the staffs",
                    "time": "2022-08-24T11:36:00+08:00"
                }
            ],
            "pricing": "",
            "companyDisplayName": "One Kampong Gelam",
            "supportedLanguage": [
                "EN"
            ],
            "amenities": "",
            "businessHour": [],
            "contact": {
                "primaryContactNo": "+6587861948",
                "secondaryContactNo": ""
            },
            "nearestMrtStation": "Bugis MRT Station",
            "officialWebsite": "http://www.afterwit.sg",
            "officialEmail": "ask@afterwit.sg",
            "staYear": "",
            "group": "",
            "temporarilyClosed": "",
            "businessHourNotes": {
                "notes": ""
            }
        }
    ],
    "paginationLinks": {
        "self": "https://api.stb.gov.sg/content/common/v2/search?offset=0&limit=1&keyword=mexican&dataset=food_beverages,tours,shops,attractions,events",
        "first": "https://api.stb.gov.sg/content/common/v2/search?offset=0&limit=1&keyword=mexican&dataset=food_beverages,tours,shops,attractions,events",
        "next": "https://api.stb.gov.sg/content/common/v2/search?offset=1&limit=1&keyword=mexican&dataset=food_beverages,tours,shops,attractions,events"
    },
    "totalRecords": 12,
    "retrievedRecords": 1
}
     */

     /*SAMPLE LOCATION
      * {
    "status": {
        "code": 200,
        "name": "OK",
        "message": "Content retrieved successfully."
    },
    "data": [
        {
            "uuid": "0022ab5439007434431af0a10a5a343337b",
            "name": "Sentosa Nature Discovery",
            "type": "Nature & Wildlife",
            "tags": [
                "Wildlife",
                "Sentosa",
                "Attractions"
            ],
            "description": "Embark on a hands-on journey of discovery and sharpen your detective skills. At Sentosa Nature Discovery, nature and fun go hand-in-hand.",
            "body": "<p>Get set for a hands-on journey of discovery and sharpen your detective skills. At Sentosa Nature Discovery, nature and fun go hand-in-hand.</p> <p>The exploration starts with a fascinating gallery of interactive exhibits at the Gallery to brush up your Science Process Skills: Observe, Classify, Compare and Infer. Equipped with these skills, step out into the jungle and explore a wilderness teeming with birds, insects, other wildlife and plants in all manner of different habitats.</p>",
            "rating": 4.4,
            "location": {
                "latitude": 1.255842,
                "longitude": 103.817397
            },
            "address": {
                "block": "",
                "streetName": "40 Imbiah Road",
                "floorNumber": "",
                "unitNumber": "",
                "buildingName": "",
                "postalCode": "099702"
            },
            "thumbnails": [
                {
                    "uuid": "101041adddb26f347d8807b88159a4f040b",
                    "url": "",
                    "libraryUuid": "1043f8acc09dae8432faa7efa88e8e92d9d",
                    "primaryFileMediumUuid": "101041adddb26f347d8807b88159a4f040b"
                }
            ],
            "images": [
                {
                    "uuid": "101babac7de7f4c42b6a919320c95513f59",
                    "url": "",
                    "libraryUuid": "1049d44ece419454b268fea65d030576eee",
                    "primaryFileMediumUuid": "101e59dec3dfe164072a4092da84998df33"
                },
                {
                    "uuid": "10199132d8e60654b308948ac2471aa787a",
                    "url": "",
                    "libraryUuid": "1043f8acc09dae8432faa7efa88e8e92d9d",
                    "primaryFileMediumUuid": "101041adddb26f347d8807b88159a4f040b"
                },
                {
                    "uuid": "101e3da8f55838240ee8bdef2387374a061",
                    "url": "",
                    "libraryUuid": "104d3c9438b4877407a8032a77b60105dc7",
                    "primaryFileMediumUuid": "101312b22a9428942348344180801073f6b"
                }
            ],
            "videos": [],
            "documents": [],
            "source": "Sentosa Development Corporation",
            "metadata": {
                "updatedDate": "2022-12-29T15:40:54+08:00",
                "createdDate": "2018-04-06T18:21:00+08:00"
            },
            "categoryDescription": "Attractions",
            "dataset": "attractions",
            "reviews": [],
            "pricing": {
                "others": "Free"
            },
            "companyDisplayName": "Sentosa Development Corporation",
            "supportedLanguage": [
                "EN"
            ],
            "amenities": "",
            "businessHour": [
                {
                    "sequenceNumber": 1,
                    "day": "daily",
                    "daily": true,
                    "openTime": "09:00",
                    "closeTime": "17:00",
                    "description": ""
                }
            ],
            "contact": {
                "primaryContactNo": "",
                "secondaryContactNo": ""
            },
            "nearestMrtStation": "",
            "officialWebsite": "www.sentosa.com.sg/en/things-to-do/attractions/sentosa-nature-discovery/",
            "officialEmail": "nature@sentosa.com.sg",
            "staYear": "",
            "admissionInfo": "For Guided Tours contact us at 1800- SENTOSA (736 8672) or email to nature@sentosa.com.sg.",
            "ticketed": "",
            "group": "SGClean",
            "temporarilyClosed": "N",
            "businessHourNotes": {
                "notes": ""
            }
        }
    ],
    "paginationLinks": {
        "self": "https://api.stb.gov.sg/content/common/v2/search?offset=0&limit=1&keyword=sentosa&dataset=food_beverages,tours,shops,attractions,events",
        "first": "https://api.stb.gov.sg/content/common/v2/search?offset=0&limit=1&keyword=sentosa&dataset=food_beverages,tours,shops,attractions,events",
        "next": "https://api.stb.gov.sg/content/common/v2/search?offset=1&limit=1&keyword=sentosa&dataset=food_beverages,tours,shops,attractions,events"
    },
    "totalRecords": 112,
    "retrievedRecords": 1
}
      */
}
