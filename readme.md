TODOS:
1) implement delete user for transactional requirement, delete user and all related comments together [SOLVED]
2) delete comment function, need to delete picture from s3 [SOLVED]
3) remove and clear form after adding comment [SOLVED]
4) new s3 added but retrieval of image from s3 not working [SOLVED]
5) move profile and logout button to side nav bar
6) if not logged in, either implement users can use app without login but cannot save and comment, or redirect to login [SOLVED]
7) align google maps and location image
8) find image not only with url but uuid as well
9) github actions [CANCELLED] #will not continue unless able to implement with docker
10) dockerfile
11) results view cards shrinking, hover
12) comment form material, buttons alignment



SAMPLE API OUTPUT
https://api.stb.gov.sg/content/common/v2/search?dataset=food_beverages,tours,shops,attractions,events&limit=1&keyword=dog
{
    "status": {
        "code": 200,
        "name": "OK",
        "message": "Content retrieved successfully."
    },
    "data": [
        {
            "uuid": "0058c09e84181fd4870ac32c18972127f21",
            "name": "Shake Shack",
            "type": "Others",
            "tags": [
                "Restaurants"
            ],
            "description": "Description",
            "body": "Shake Shack offers delicious burgers, chicken, hot dogs, frozen custard, beer, wine and more to satisfy your cravings, which you can enjoy in the comfortable and stylish ambience the cafe has to offer.",
            "rating": 4,
            "location": {
                "latitude": 1.28218,
                "longitude": 103.84415
            },
            "address": {
                "block": "",
                "streetName": "89 Neil Road #01-01,",
                "floorNumber": "",
                "unitNumber": "",
                "buildingName": "",
                "postalCode": "088849"
            },
            "thumbnails": [],
            "images": [
                {
                    "uuid": "",
                    "url": "https://chinatown.sg/wp-content/uploads/2020/06/shake-shack.jpg",
                    "libraryUuid": "",
                    "primaryFileMediumUuid": ""
                }
            ],
            "videos": [],
            "documents": [],
            "cuisine": "American, Fast Food",
            "source": "Chinatown Business Association",
            "metadata": {
                "updatedDate": "2020-08-24T17:20:19+08:00",
                "createdDate": "2020-08-12T15:01:04+08:00"
            },
            "categoryDescription": "Food & Beverages",
            "dataset": "food_beverages",
            "reviews": [
                {
                    "authorName": "Sabrena Arosh",
                    "authorURL": "https://www.google.com/maps/contrib/107321441261892270021/reviews",
                    "language": "en",
                    "profilePhoto": "https://lh3.googleusercontent.com/a-/AFdZucpH612NQq7QN-g20XnZ_Hihdlt5WN6CVk4V4D606w=s128-c0x00000000-cc-rp-mo-ba5",
                    "rating": 5,
                    "text": "Nice place for burgers and shakes if you’re just off a flight. Shake Shack has built up a reputation for itself and it does attract a steady crowd at this outlet. Prices are reasonable and the food is good, standard burger taste and size. Beer is nice as well and fairly affordable.",
                    "time": "2022-08-30T06:12:18+08:00"
                },
                {
                    "authorName": "Daryl Lim",
                    "authorURL": "https://www.google.com/maps/contrib/100732527554716603480/reviews",
                    "language": "en",
                    "profilePhoto": "https://lh3.googleusercontent.com/a/AItbvmnv1ueqWBgTk9qrCAUZl_sFQm97E5oa-fkTY4U9=s128-c0x00000000-cc-rp-mo",
                    "rating": 5,
                    "text": "Frankly, my most favourite to go burger joint in Singapore. Shake Shack in my opinion serves the best buns, patty and fries and goodness gracious they tastes extremely good.\nBeen visiting the outlet at Jewel since their opening, never fails to disappoint. Beats Five Guys hands down every single time. Keep up the good work!",
                    "time": "2022-07-25T12:28:37+08:00"
                },
                {
                    "authorName": "Jonas Andersen",
                    "authorURL": "https://www.google.com/maps/contrib/103814175196959831266/reviews",
                    "language": "en",
                    "profilePhoto": "https://lh3.googleusercontent.com/a/AItbvmnC2Rr60VuSe5XGWDFtPEGtfk_PydcAQCuekecd=s128-c0x00000000-cc-rp-mo-ba3",
                    "rating": 5,
                    "text": "Great burgers and fries right at the entrance to the mall. Tried the chicken poppers with honey mustard dipping sauce for the first time and they were great! They have self order kiosks at the front, so placing your order is easy and stress free. The wait for the food is about 10-15 minutes. On the day we visited it was busy, but there were enough tables for everyone.",
                    "time": "2022-05-02T09:36:10+08:00"
                },
                {
                    "authorName": "Ken Ang",
                    "authorURL": "https://www.google.com/maps/contrib/117429118778739082415/reviews",
                    "language": "en",
                    "profilePhoto": "https://lh3.googleusercontent.com/a-/AFdZucquOQEManqTULsuuiPkN0kXnOWxFAvKceO8x8ZTPmA=s128-c0x00000000-cc-rp-mo-ba4",
                    "rating": 5,
                    "text": "It's shake shack. Pretty good burgers and fries. Nice to see that they always have local flavors.\nThe smoke shack, is an explosion of flavors, from the bacon, the patty, the pickles and the study fluffy bun. Awesome!",
                    "time": "2022-07-27T12:06:13+08:00"
                },
                {
                    "authorName": "andrea sng",
                    "authorURL": "https://www.google.com/maps/contrib/113147098618251217794/reviews",
                    "language": "en",
                    "profilePhoto": "https://lh3.googleusercontent.com/a/AItbvmka5JcSNOoq_sz7684ZlvGlWCOQLzNrgKjtfCTl=s128-c0x00000000-cc-rp-mo",
                    "rating": 5,
                    "text": "This place is so cute! Our cashier was so nice!!! I went on March 12 at about 2pm. I think her name tag said Lil Jo I'm not sure if that's her real name but she's the best! She was so friendly and nice and food was good too. Overall 10/10 would come again!!!",
                    "time": "2022-03-12T06:01:03+08:00"
                }
            ],
            "pricing": "",
            "companyDisplayName": "Chinatown Business Association",
            "supportedLanguage": [
                "EN"
            ],
            "amenities": "",
            "businessHour": [],
            "contact": {
                "primaryContactNo": "",
                "secondaryContactNo": ""
            },
            "nearestMrtStation": "Outram MRT Station",
            "officialWebsite": "https://www.shakeshack.com.sg/main/location_neil",
            "officialEmail": "sharewithus@shakeshack.com.sg",
            "staYear": "",
            "group": "",
            "temporarilyClosed": "",
            "businessHourNotes": {
                "notes": ""
            }
        }
    ],
    "paginationLinks": {
        "self": "https://api.stb.gov.sg/content/common/v2/search?offset=0&limit=1&keyword=dog&dataset=food_beverages,tours,shops,attractions,events",
        "first": "https://api.stb.gov.sg/content/common/v2/search?offset=0&limit=1&keyword=dog&dataset=food_beverages,tours,shops,attractions,events",
        "next": "https://api.stb.gov.sg/content/common/v2/search?offset=1&limit=1&keyword=dog&dataset=food_beverages,tours,shops,attractions,events"
    },
    "totalRecords": 3,
    "retrievedRecords": 1
}

https://api.stb.gov.sg/media/download/v2/1016276128be9ad447fb41f13144c3144c1

Opts:
Add in one to one RS for mySQL
check errors for loading individual pages --> consider moving methods to load details on new pages than old