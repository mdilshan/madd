package com.example.madd.util;

import com.example.madd.ReviewInterface;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static enum ResourceTypes {
        HOTEL,
        PLACE,
        GUIDE
    }

    public static double getAverage(int length, double sum) throws NumberFormatException {
        if (length == 0) {
            throw new NumberFormatException();
        }
        double cal = sum / length;
        return roundFloat(cal, 1);
    }

    public static double roundFloat(double d, int place) {
        DecimalFormat df = new DecimalFormat("0.0");
        BigDecimal bd = new BigDecimal(Double.toString(d));
        bd = bd.setScale(place, RoundingMode.HALF_UP);
        System.out.println(bd.floatValue());
        return bd.doubleValue();
    }

    /**
     * Use to seed the review data collection on firestore
     */
    public static void seedReview() {
        ArrayList<ReviewInterface> items = new ArrayList<ReviewInterface>();
        items.add(new ReviewInterface("user_23", "Swyatha ", "2020-09-12", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt", 3.0));
        items.add(new ReviewInterface("user_24", "Suwathi", "2020-09-12", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitatio", 1.8));
        items.add(new ReviewInterface("user_25", "Dilshan", "2020-09-12", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt", 4.0));
        items.add(new ReviewInterface("user_26", "Ashiq", "2020-09-01", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitatio", 4.8));
        items.add(new ReviewInterface("user_27", "Kamal", "2020-09-12", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt", 3.0));
        items.add(new ReviewInterface("user_28", "Shrimali", "2020-09-12", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitatio", 1.8));
        items.add(new ReviewInterface("user_29", "Gihan", "2020-09-12", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt", 4.0));
        items.add(new ReviewInterface("user_30", "Kavishka", "2020-09-01", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitatio", 4.8));
        items.add(new ReviewInterface("user_31", "Pramuka", "2020-09-12", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt", 3.0));
        items.add(new ReviewInterface("user_32", "Lahiru", "2020-09-12", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitatio", 1.8));
        items.add(new ReviewInterface("user_33", "Madushanka", "2020-09-12", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt", 4.0));
        items.add(new ReviewInterface("user_34", "Praveen", "2020-09-01", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitatio", 4.8));
        items.add(new ReviewInterface("user_35", "Kalinga", "2020-09-12", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt", 3.0));
        items.add(new ReviewInterface("user_36", "Dilanka", "2020-09-12", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitatio", 1.8));
        items.add(new ReviewInterface("user_37", "Dinuka", "2020-09-12", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt", 4.0));
        items.add(new ReviewInterface("user_38", "Isanka", "2020-09-01", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitatio", 4.8));

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        for (ReviewInterface item : items) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("resource_id", "zIC1noLoA4HlJrVcnAAs");
            data.put("comment", item.comment);
            data.put("name", item.name);
            data.put("posted_by", item.posted_by);
            data.put("rating", item.rating);
            data.put("user_id", "user_34");

            db.collection("reviews").add(data)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (documentSnapshot.exists()) {
                                    }
                                }
                            });
                        }
                    });
        }
    }

    public static void seedGuids() {
        Map<String, Object> data1 = new HashMap<>();
        data1.put("guide_name", "Pramitha");
        data1.put("place", "Kandy");
        data1.put("mobile", "0750128988");
        data1.put("imageUrl", "https://images.unsplash.com/photo-1480455624313-e29b44bbfde1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80");
        data1.put("about", "A highly motivated Tour Guide who is a natural communicator and able to provide an entertaining and informative excursion to her customers. Maxine has a flair for talking to groups of people along with a good memory for facts and figures. She has always been a fantastic storyteller with an infectious sense of humour and is an expert at thinking quickly on her feet. In her current role, she provides tourist with a unique, unforgettable experience that encourages them to fully participate with what is going on around them. ");
        data1.put("rating", "4.3");
        data1.put("joined_on", "2020-09-04");

        Map<String, Object> data2 = new HashMap<>();
        data2.put("guide_name", "Shachin jayakanth");
        data2.put("place", "Colombo");
        data2.put("mobile", "0770778988");
        data2.put("imageUrl", "https://i.pinimg.com/originals/7a/a0/39/7aa03953f1d4f36cbe7a48a5727b0995.jpg");
        data2.put("about", "A highly motivated Tour Guide who is a natural communicator and able to provide an entertaining and informative excursion to her customers. Maxine has a flair for talking to groups of people along with a good memory for facts and figures. She has always been a fantastic storyteller with an infectious sense of humour and is an expert at thinking quickly on her feet. In her current role, she provides tourist with a unique, unforgettable experience that encourages them to fully participate with what is going on around them. ");
        data2.put("rating", "4.9");
        data2.put("joined_on", "2020-10-04");

        Map<String, Object> data12 = new HashMap<>();
        data12.put("guide_name", "Thaanish Ahamed");
        data12.put("place", "Kegalle");
        data12.put("mobile", "0743778988");
        data12.put("imageUrl", "https://images.unsplash.com/photo-1492562080023-ab3db95bfbce?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=80");
        data12.put("about", "A highly motivated Tour Guide who is a natural communicator and able to provide an entertaining and informative excursion to her customers. Maxine has a flair for talking to groups of people along with a good memory for facts and figures. She has always been a fantastic storyteller with an infectious sense of humour and is an expert at thinking quickly on her feet. In her current role, she provides tourist with a unique, unforgettable experience that encourages them to fully participate with what is going on around them. ");
        data12.put("rating", "5.0");
        data12.put("joined_on", "2020-10-01");

        Map<String, Object> data11 = new HashMap<>();
        data11.put("guide_name", "Hasan Salih");
        data11.put("place", "Minuwangoda");
        data11.put("mobile", "0754778845");
        data11.put("imageUrl", "https://efmireland.ie/wp-content/uploads/2016/07/man-838636_960_720.jpg");
        data11.put("about", "A highly motivated Tour Guide who is a natural communicator and able to provide an entertaining and informative excursion to her customers. Maxine has a flair for talking to groups of people along with a good memory for facts and figures. She has always been a fantastic storyteller with an infectious sense of humour and is an expert at thinking quickly on her feet. In her current role, she provides tourist with a unique, unforgettable experience that encourages them to fully participate with what is going on around them. ");
        data11.put("rating", "4.2");
        data11.put("joined_on", "2020-10-03");

        Map<String, Object> data13 = new HashMap<>();
        data13.put("guide_name", "Javahar Balraj");
        data13.put("place", "Navalapitya");
        data13.put("mobile", "0770778988");
        data13.put("imageUrl", "https://images.everydayhealth.com/images/mens-health/6-skincare-tips-men-should-always-follow-peter-kraus-00-722x406.jpg");
        data13.put("about", "A highly motivated Tour Guide who is a natural communicator and able to provide an entertaining and informative excursion to her customers. Maxine has a flair for talking to groups of people along with a good memory for facts and figures. She has always been a fantastic storyteller with an infectious sense of humour and is an expert at thinking quickly on her feet. In her current role, she provides tourist with a unique, unforgettable experience that encourages them to fully participate with what is going on around them. ");
        data13.put("rating", "3.5");
        data13.put("joined_on", "2020-09-11");

        Map<String, Object> data14 = new HashMap<>();
        data14.put("guide_name", "Chamudhitha Wikramasinghe");
        data14.put("place", "Matale");
        data14.put("mobile", "0770778988");
        data14.put("imageUrl", "https://img-cdn.tid.al/o/722d6029152671b421806cf3a8aff0e328ed03bf.jpg");
        data14.put("about", "A highly motivated Tour Guide who is a natural communicator and able to provide an entertaining and informative excursion to her customers. Maxine has a flair for talking to groups of people along with a good memory for facts and figures. She has always been a fantastic storyteller with an infectious sense of humour and is an expert at thinking quickly on her feet. In her current role, she provides tourist with a unique, unforgettable experience that encourages them to fully participate with what is going on around them. ");
        data14.put("rating", "0.0");
        data14.put("joined_on", "2019-10-04");

        Map<String, Object> data15 = new HashMap<>();
        data15.put("guide_name", "janith Warnasooriya");
        data15.put("place", "Kengalla");
        data15.put("mobile", "0770778988");
        data15.put("imageUrl", "https://www.indiewire.com/wp-content/uploads/2019/07/shutterstock_10312373e.jpg?w=780");
        data15.put("about", "A highly motivated Tour Guide who is a natural communicator and able to provide an entertaining and informative excursion to her customers. Maxine has a flair for talking to groups of people along with a good memory for facts and figures. She has always been a fantastic storyteller with an infectious sense of humour and is an expert at thinking quickly on her feet. In her current role, she provides tourist with a unique, unforgettable experience that encourages them to fully participate with what is going on around them. ");
        data15.put("rating", "3.0");
        data15.put("joined_on", "2020-09-10");

        Map<String, Object> data16 = new HashMap<>();
        data16.put("guide_name", "Amandha Rathnayaka");
        data16.put("place", "Kurunegala");
        data16.put("mobile", "0755578988");
        data16.put("imageUrl", "https://1.bp.blogspot.com/-nCstXnNGz2U/WpuZ_hLjGeI/AAAAAAAADYA/Vj2DtgGAseEW6ecKi0tqgQDpokNhPdeRACLcBGAs/s1600/professional-women-with-blue-shirt.jpg");
        data16.put("about", "A highly motivated Tour Guide who is a natural communicator and able to provide an entertaining and informative excursion to her customers. Maxine has a flair for talking to groups of people along with a good memory for facts and figures. She has always been a fantastic storyteller with an infectious sense of humour and is an expert at thinking quickly on her feet. In her current role, she provides tourist with a unique, unforgettable experience that encourages them to fully participate with what is going on around them. ");
        data16.put("rating", "3.0");
        data16.put("joined_on", "2020-10-05");

        Map<String, Object> data17 = new HashMap<>();
        data17.put("guide_name", "Marie Curie");
        data17.put("place", "Anuradhapura");
        data17.put("mobile", "0770775558");
        data17.put("imageUrl", "https://www.cuinsight.com/wp-content/uploads/2018/10/bigstock-Young-Beautiful-Business-Woman-225363058.jpg");
        data17.put("about", "A highly motivated Tour Guide who is a natural communicator and able to provide an entertaining and informative excursion to her customers. Maxine has a flair for talking to groups of people along with a good memory for facts and figures. She has always been a fantastic storyteller with an infectious sense of humour and is an expert at thinking quickly on her feet. In her current role, she provides tourist with a unique, unforgettable experience that encourages them to fully participate with what is going on around them. ");
        data17.put("rating", "5.0");
        data17.put("joined_on", "2020-09-10");

        Object[] list = {data1, data2, data11, data12, data13, data14, data15, data16, data16};
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        for (Object item : list) {
            db.collection("guides").add(item)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (documentSnapshot.exists()) {
                                    }
                                }
                            });
                        }
                    });
        }
    }


    public static void seedPlaces() {
        HashMap<String,Object>d1=new HashMap<>();
        d1.put("imageUrl","https://etemple.com/imagestoimport/Temple/117289/Image/img_29512.jpeg");
        d1.put("joined_on","2020-10-04");
        d1.put("place_URL","https://etemple.com/imagestoimport/Temple/117289/Image/img_29512.jpeg");
        d1.put("place_description","Developed from 205 BC, the original kovil combined key features to form its basic Dravidian temple plan, such as its thousand pillared hall – 'Aayiram Kaal Mandapam' – and the Jagati expanded by King Elara Manu Needhi Cholan. Regarded as the greatest building of its age for its architecture, elaborate sculptural bas-relief ornamentation adorned a black granite megalith while its multiple gold plated gopuram towers were expanded in the medieval period. One of three major Hindu shrines on the promontory with a colossal gopuram tower, it stood distinctly on the cape's highest eminence. The journey for pilgrims in the town begins at the opening of Konesar Road and follows a path through courtyard shrines of the compound to the deities Bhadrakali, Ganesh, Vishnu Thirumal, Surya, Raavana, Ambal-Shakti, Murukan and Shiva who presides at the promontory's height. The annual Koneswaram Temple Ther Thiruvilah festival involves the Bhadrakali temple of Trincomalee, the Pavanasam Theertham at the preserved Papanasuchunai holy well and the proximal Back Bay Sea (Theertham Karatkarai) surrounding Konesar Malai.");
        d1.put("place_location","Trincomalee");
        d1.put("place_name","Thirukoneshwaram");
        d1.put("rating","0.0");

        HashMap<String,Object>d2=new HashMap<>();
        d2.put("imageUrl","https://c8.alamy.com/comp/KWYGP4/temple-of-the-sacred-tooth-relic-or-sri-dalada-maligawa-in-kandy-at-KWYGP4.jpg");
        d2.put("joined_on","2020-10-04");
        d2.put("place_URL","https://c8.alamy.com/comp/KWYGP4/temple-of-the-sacred-tooth-relic-or-sri-dalada-maligawa-in-kandy-at-KWYGP4.jpg");
        d2.put("place_description","Sri Dalada Maligawa or the Temple of the Sacred Tooth Relic is a Buddhist temple in the city of Kandy, Sri Lanka. It is located in the royal palace complex of the former Kingdom of Kandy, which houses the relic of the tooth of the Buddha. Since ancient times, the relic has played an important role in local politics because it is believed that whoever holds the relic holds the governance of the country. Kandy was the last capital of the Sri Lankan kings and is a World Heritage Site mainly due to the temple.");
        d2.put("place_location","Kandy");
        d2.put("place_name","Temple of Tooth relic");
        d2.put("rating","3.8");

        HashMap<String,Object>d3=new HashMap<>();
        d3.put("imageUrl","https://www.amayaresorts.com/blog/wp-content/uploads/sites/3/2018/07/Pinnawala-Elephant-Orphanage-Amaya-Hills-Kandy-Hotels-in-Kandy.jpg");
        d3.put("joined_on","2020-10-03");
        d3.put("place_URL","https://www.amayaresorts.com/blog/wp-content/uploads/sites/3/2018/07/Pinnawala-Elephant-Orphanage-Amaya-Hills-Kandy-Hotels-in-Kandy.jpg");
        d3.put("place_description","The Pinnawala Elephant Orphanage was established by the Sri Lankan Department of Wildlife Conservation in 1975 for feeding and providing care and sanctuary to orphaned baby elephants that were found in the wild. The orphanage was located at the Wilpattu National Park, then shifted to the tourist complex at Bentota and then to the Dehiwala Zoo. From the Zoo it was shifted to Pinnawala village on a 25-acre (10 ha) coconut plantation adjacent to the Maha Oya River.The primary residential care area is on the east side of Highway B199, Rambukkana Road. The main site also has some restaurants and refreshment stands, and management buildings including sleeping sheds and veterinary facilities. The elephant bathing and viewing area along the Oya River is directly opposite on the west side of the highway.[3][4]At the time it was settled, the orphanage had five baby elephants which formed its nucleus. The addition of orphans continued till 1995 when the Elephant Transit Home (ETH) adjoining Udawalawe National Park was created by the DWC. Since then, orphaned babies have been taken to the ETH and addition to the Pinnawala herd has been mostly through births occurring there.[1]");
        d3.put("place_location","Pinawala");
        d3.put("place_name","Elephant Orphanage");
        d3.put("rating","0.0");

        HashMap<String,Object>d4=new HashMap<>();
        d4.put("imageUrl","https://www.steuartholidays.com/blog/wp-content/uploads/sites/9/2019/03/Dambulla-cave-tmeple.jpg");
        d4.put("joined_on","2020-10-02");
        d4.put("place_URL","https://www.steuartholidays.com/blog/wp-content/uploads/sites/9/2019/03/Dambulla-cave-tmeple.jpg");
        d4.put("place_description","Dambulla is the largest and best-preserved cave temple complex in Sri Lanka. The rock towers 160 m over the surrounding plains. There are more than 80 documented caves in the surrounding area. Major attractions are spread over five caves, which contain statues and paintings. These paintings and statues are related to Gautama Buddha and his life. There are a total of 153 Buddha statues, three statues of Sri Lankan kings and four statues of gods and goddesses. The latter include Vishnu and the Ganesha. The murals cover an area of 2,100 square metres (23,000 sq ft). Depictions on the walls of the caves include the temptation by the demon Mara, and Buddha's first sermon.");
        d4.put("place_location","Dambulla");
        d4.put("place_name","Cave Temple");
        d4.put("rating","2.0");

        HashMap<String,Object>d5=new HashMap<>();
        d5.put("imageUrl","https://www.timetravelturtle.com/wp-content/uploads/2015/01/Sri-Lanka-2014-679_feat.jpg");
        d5.put("joined_on","2020-10-01");
        d5.put("place_URL","https://www.timetravelturtle.com/wp-content/uploads/2015/01/Sri-Lanka-2014-679_feat.jpg");
        d5.put("place_description","The Horton Plains are the headwaters of three major Sri Lankan rivers, the Mahaweli, Kelani, and Walawe. In Sinhala the plains are known as Maha Eliya Plains (මහ එළිය තැන්න). Stone tools dating back to Balangoda culture have been found here. The plains' vegetation is grasslands interspersed with montane forest and includes many endemic woody plants. Large herds of Sri Lankan sambar deer feature as typical mammals and the park is also an Important Bird Area with many species not only endemic to Sri Lanka but restricted to the Horton Plains. Forest dieback is one of the major threats to the park and some studies suggest that it is caused by a natural phenomenon.");
        d5.put("place_location","Nuwareliya");
        d5.put("place_name","Hortain Plains");
        d5.put("rating","0.0");

        HashMap<String,Object>d6=new HashMap<>();
        d6.put("imageUrl","https://www.srilankatravelandtourism.com/places-sri-lanka/bentota/bentota-images/bentota-4-sri-lanka.jpg");
        d6.put("joined_on","2020-10-04");
        d6.put("place_URL","https://www.srilankatravelandtourism.com/places-sri-lanka/bentota/bentota-images/bentota-4-sri-lanka.jpg");
        d6.put("place_description","Bentota, a mere 45 minutes away from Colombo along the Southern express way, hails as a premier resort town with no less than 15 Star class Hotels clustered together plus numerous other less than 10 rooms boutique style hotels with Sun, surf and sand. This gives the visitor an ideal chance to stay in one and savour many different above water activities, Bentota has always been renowned as the water sport capital of Sri Lanka with the hoteliers in the area actively promoting water sports from adrenaline filled speed boat and jet ski riding, wind surfing, boogie boarding, water skiing, kite surfing, to a leisurely ride on a banana boat for the family & kids.");
        d6.put("place_location","Benthota");
        d6.put("place_name","Benthota Beach");
        d6.put("rating","0.0");

        HashMap<String,Object>d7=new HashMap<>();
        d7.put("imageUrl","https://res.klook.com/image/upload/c_fill,w_750,h_500,f_auto/w_80,x_15,y_15,g_south_west,l_klook_water/activities/unernd2ltijsas04tym9.jpg");
        d7.put("joined_on","2020-10-04");
        d7.put("place_URL","https://res.klook.com/image/upload/c_fill,w_750,h_500,f_auto/w_80,x_15,y_15,g_south_west,l_klook_water/activities/unernd2ltijsas04tym9.jpg");
        d7.put("place_description","According to International Union for Conservation of Nature (IUCN), Sinharaja is the country's last viable area of primary tropical rainforest. More than 60% of the trees are endemic and many of them are considered rare. 50% of Sri Lankan's endemics species of animals (specially butterfly, amphibians and fish species). It's home to 95% endemic birds.The hilly virgin rainforest, part of the Sri Lanka lowland rain forests ecoregion, was saved from the worst of commercial logging by its inaccessibility, and was designated a World Biosphere Reserve in 1978 and a World Heritage Site in 1988 Because of the dense vegetation, wildlife is not as easily seen as at dry-zone national parks such as Yala. There are about 3 elephants, and 15 or so[vague] leopards. The most common larger mammal is the endemic purple-faced langur.");
        d7.put("place_location","Sinharaja");
        d7.put("place_name","Sinharaja Reserve forest");
        d7.put("rating","1.0");

        HashMap<String,Object>d8=new HashMap<>();
        d8.put("imageUrl","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTeQzESYyGlEq13vn7fEXhUBTZGj1xedv_9Qw&usqp=CAU");
        d8.put("joined_on","2020-09-04");
        d8.put("place_URL","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTeQzESYyGlEq13vn7fEXhUBTZGj1xedv_9Qw&usqp=CAU");
        d8.put("place_description","Other prominent landmarks in Galle include the city's natural harbour, the National Maritime Museum, St. Mary's Cathedral founded by Jesuit priests, one of the main Shiva temples on the island, and Amangalla, the historic luxury hotel. On 26 December 2004, the city was devastated by the massive tsunami caused by the 2004 Indian Ocean earthquake, which occurred off the coast of Indonesia a thousand miles away. Thousands were killed in the city alone. Galle is home to the Galle International Stadium, which is considered to be one of the most picturesque cricket grounds in the world.[3] The ground, which was severely damaged by the tsunami, was rebuilt and test matches resumed there on 18 December 2007.");
        d8.put("place_location","Galle");
        d8.put("place_name","Old town of Galle");
        d8.put("rating","0.0");

        Object[] list = {d1, d2, d3, d4, d5, d6, d7, d8};
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        for (Object item : list) {
            db.collection("places").add(item)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (documentSnapshot.exists()) {
                                    }
                                }
                            });
                        }
                    });
        }
    }

    public static void seedHotels() {
        Map<String, Object> dataH1 = new HashMap<>();
        dataH1.put("hotel_name","Hotel The Serras");
        dataH1.put("about","This luxury boutique hotel comprises 28 trendy and very spacious rooms and suites, a Michelin star Chef Restaurant and a chill out rooftop terrace.");
        dataH1.put("contact","0779316919");
        dataH1.put("image","https://miro.medium.com/max/6912/1*Vap_oj28_mfikW3R8kuCXg.jpeg");
        dataH1.put("joined_on","2020-10-01");
        dataH1.put("location","Matale");
        dataH1.put("rating","3.5");

        Map<String, Object> dataH3 = new HashMap<>();
        dataH3.put("hotel_name","Anilana Pasikuda");
        dataH3.put("about","Anilana Passikuda is a lush hideaway built with breathtaking views of the Indian Ocean, situated in the northen coastal belt on the world famous Pasikuda Bay. You’ll quickly embrace the exhilarating atmosphere in a tropical heaven, tailored to those who prefer a quiet retreat or a lively holiday.");
        dataH3.put("contact","0112222973");
        dataH3.put("image","https://assets.tivolihotels.com/image/upload/q_auto,f_auto/media/minor/tivoli/images/brand_level/footer/1920x1000/thr_aboutus1_1920x1000.jpg");
        dataH3.put("joined_on","2020-09-10");
        dataH3.put("location","Ampara");
        dataH3.put("rating","2.5");

        Map<String, Object> dataH4 = new HashMap<>();
        dataH4.put("hotel_name","Marino Beach");
        dataH4.put("about","Marino Beach Colombo, star classed hotel is located between the Galle Road and Colombo Plan Road more commonly known as Marine Drive. Hotel comprises Restaurants, Bars, Banquet Facilities, Fitness Centre. It also comprises an ‘Infinity Pool’ with a breath-taking view over the Indian Ocean and Jacuzzis to unwind yourself. Equipped with 289 spaciously comfortable rooms & Suites with views over the Indian Ocean and our 24/7 Housekeeping and Guest Service functions that are exceptionally standards driven which you are assured of best hotel experience.Adjoining Marino Mall gives you the latest entertainments and shopping experience in town for both families and individual travelers");
        dataH4.put("contact","0252264489");
        dataH4.put("image","https://assets.anantara.com/image/upload/q_auto,f_auto/media/minor/anantara/images/anantara-vacation-club-legian-bali/the-resort/anantara_vacation_club_legian_pool_teaser_750x405.jpg");
        dataH4.put("joined_on","2020-03-15");
        dataH4.put("location","Colombo");
        dataH4.put("rating","2.7");

        Map<String, Object> dataH5 = new HashMap<>();
        dataH5.put("hotel_name","Ceylon City Hotel");
        dataH5.put("about","Ceylon City Hotel, situated at 11, Upatissa Road, Colombo-04 is the latest hotel to join the Sri Lankan Hospitality Industry and with the modern facilities available Ceylon City Hotel is one of the finest business and leisure hotels in Colombo. Ceylon City Hotel comprises of 3 rooms categories namely Junior Suite, Deluxe and Standard rooms and all rooms consist of King Size Beds, Mini Bar, 32 LCD TV with satellite connection, Air Condition, Spacious Sofa, Coffee Table, TV Table, Dressing Table and Hospitality tray with tea coffee making facilities. In addition to these facilities Ceylon City also has 24-hour room service, very spacious Restaurant that also can be used for special occasions, conference hall and a very unique eye catching lobby and a coffee shop. Due to excellence in Service & Quality Level Trip Advisor named Ceylon City Hotel as one Traveller Choice award winners in 2016. Ceylon City Hotel was named 'Best Budget Hotel in Sri Lanka' for 2015 by Hotel of the Year Awards");
        dataH5.put("contact","0112508000");
        dataH5.put("image","https://www.riuagents.com/agents/en/binaris/swimming-pool-hotel-riu-sri-lanka-3_tcm93-194730.jpg");
        dataH5.put("joined_on","2020-07-19");
        dataH5.put("location","Mannar");
        dataH5.put("rating","4.0");

        Map<String, Object> dataH6 = new HashMap<>();
        dataH6.put("hotel_name","Jetwing Colombo Seven");
        dataH6.put("about","If you’re looking for a family-friendly hotel in Colombo, look no further than Jetwing Colombo Seven.");
        dataH6.put("contact","0114709400");
        dataH6.put("image","https://www.thekingsburyhotel.com/wp-content/uploads/2020/01/Kingsbury-slide-5-m.jpg");
        dataH6.put("joined_on","2020-04-14");
        dataH6.put("location","Colombo");
        dataH6.put("rating","4.5");

        Map<String, Object> dataH7 = new HashMap<>();
        dataH7.put("hotel_name","Pearl Grand Hotel");
        dataH7.put("about","If you’re looking for a family-friendly hotel in Colombo, look no further than Jetwing Colombo Seven. For those interested in checking out popular landmarks while visiting Colombo, Jetwing Colombo Seven is located a short distance from Seema Malakaya Temple (1.1 mi) and Temple of Sri Kailawasanathan Swami Devasthanam Kovil (1.4 mi). The rooms offer a flat screen TV and air conditioning, and getting online is possible, as free wifi is available, allowing you to rest and refresh with ease. Jetwing Colombo Seven features a concierge, room service, and a rooftop terrace. In addition, as a valued Jetwing Colombo Seven guest, you can enjoy a rooftop pool and free breakfast that are available on-site. Guests arriving by vehicle have access to free parking. Colombo has plenty of steakhouses. So when you’re here, be sure to check out popular spots like Capital Bar & Grill, The London Grill, and Berlin Sky Lounge, which are serving up some great dishes. If you are interested in exploring Colombo, check out a government building, such as Old Parliament Building");
        dataH7.put("contact","06644545897");
        dataH7.put("image","https://content.r9cdn.net/res/images/marble/seo_hotels.jpg?v=aeb8c67f83d5b9fd53ca97055fc8402800bf3ce4&cluster=4");
        dataH7.put("joined_on","2020-12-12");
        dataH7.put("location","Hambantota");
        dataH7.put("rating","2.5");


        Map<String, Object> dataH8 = new HashMap<>();
        dataH8.put("hotel_name","Galadari Hotel");
        dataH8.put("about","Desirably located in the heart of Colombo’s the city center, Galadari Hotel excels in modern amenities sophistication and a stunning, unparalleled view of the Ocean...Step-in to be lost in cozy hideouts, pleasurable surroundings and the best of Sri Lankan hospitality...");
        dataH8.put("contact","0458947789");
        dataH8.put("image","https://www.topaz.lk/wp-content/uploads/2017/03/front-view-1.jpg");
        dataH8.put("joined_on","2020-01-14");
        dataH8.put("location","Kegalle");
        dataH8.put("rating","5.0");

        Object[] list = {dataH1, dataH3, dataH4, dataH5, dataH6, dataH7, dataH8};
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        for (Object item : list) {
            db.collection("hotels").add(item)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (documentSnapshot.exists()) {
                                    }
                                }
                            });
                        }
                    });
        }
    }
}
