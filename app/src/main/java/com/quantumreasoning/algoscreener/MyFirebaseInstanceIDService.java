package com.quantumreasoning.algoscreener;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Kendall on 10/14/2017.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private DatabaseReference mDatabase;

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        final String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        //Log.d("TOKEN", refreshedToken);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("root/user_ids");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String data = "";
                if (dataSnapshot != null && dataSnapshot.getValue() != null){
                    data = dataSnapshot.getValue().toString();
                    //Log.d("IDs", data);
                    if (!(data.contains(refreshedToken))) {
                        data = data + " " + refreshedToken;
                    }
                }
                else {
                    data = data + refreshedToken;
                }
                mDatabase.setValue(data);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //System.out.println("CANCELLED");System.out.println("CANCELLED");
            }
        });


        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        //sendRegistrationToServer(refreshedToken);
    }

}
