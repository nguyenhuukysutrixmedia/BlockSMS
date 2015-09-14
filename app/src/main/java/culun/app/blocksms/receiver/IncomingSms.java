package culun.app.blocksms.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

import culun.app.blocksms.model.MyContact;
import culun.app.blocksms.model.MySms;
import culun.app.blocksms.ormlite.DatabaseHelper;

/**
 * Created by ky.nguyen on 14/09/2015.
 */
public class IncomingSms extends BroadcastReceiver {

    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();

    @Override
    public void onReceive(Context context, Intent intent) {

        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();
        final DatabaseHelper databaseHelper = new DatabaseHelper(context);
        final RuntimeExceptionDao<MyContact, String> myContactDao = databaseHelper.getMyContactDao();

        try {

            if (bundle != null) {

                List<MyContact> myContactList = myContactDao.queryForAll();

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    long id = System.currentTimeMillis();
                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();

                    MyContact myContact = new MyContact(0, senderNum);

                    if (myContactList.contains(myContact)) {
                        MySms mySms = new MySms(id, message, phoneNumber, "tonumber");
                        databaseHelper.getMySmsDao().create(mySms);
                        abortBroadcast();
                    }

                    Log.i("SmsReceiver", "senderNum: " + senderNum + "; message: " + message);
                } // end for loop
            } // bundle is null

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" + e);
        }
    }
}
