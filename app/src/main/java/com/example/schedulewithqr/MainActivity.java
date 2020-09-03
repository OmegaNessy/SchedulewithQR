package com.example.schedulewithqr;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.schedulewithqr.model.Pair;
import com.example.schedulewithqr.service.PairService;
import com.example.schedulewithqr.service.PairServiceImpl;
import com.google.zxing.Result;

import org.json.JSONException;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private static final String TITLE = "В данном кабинете сейчас:";
    private static final String NO_PAIR = "В данный момент пар нет";
    private static final String EXCEPTION = "QR-код содержит некорректные данные.";

    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView scannerView;
    private PairService pairService;
    private static int camId = Camera.CameraInfo.CAMERA_FACING_BACK;
    private final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        int currentApiVersion = Build.VERSION.SDK_INT;
        pairService = new PairServiceImpl(this);
        if (currentApiVersion >= Build.VERSION_CODES.M) {
            if (checkPermission()) {
                Toast.makeText(getApplicationContext(),
                        "Permission already granted!", Toast.LENGTH_LONG).show();
            } else {
                requestPermission();
            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            if (checkPermission()) {
                if (scannerView == null) {
                    scannerView = new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            } else {
                requestPermission();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        scannerView.stopCamera();
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (grantResults.length > 0) {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted) {
                        Toast.makeText(getApplicationContext(),
                                "Permission Granted, Now you can access camera",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Permission Denied, You cannot access and camera",
                                Toast.LENGTH_LONG).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(CAMERA)) {
                                showMessageOKCancel(
                                        "You need to allow access to both the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{CAMERA},
                                                            REQUEST_CAMERA);
                                                }
                                            }
                                        });
                                return;
                            }
                        }
                    }
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.qr_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionGoToLogin:
                startActivity(new Intent(this, AuthActivity.class));
                finish();
                break;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUEST_CAMERA);
    }

    private boolean checkPermission() {
        return (ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public void handleResult(Result result) {
        final String myResult = result.getText();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(TITLE);
        builder.setNeutralButton("Занятия в аудитории", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    startActivity(prepareIntent(myResult));
                } catch (Exception e) {
                    builder.setMessage(EXCEPTION);
                    alterShow(builder);
                }
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                scannerView.resumeCameraPreview(MainActivity.this);
            }
        });
        try {
            Pair currentPair = pairService.getPair(myResult);
            if (currentPair.isEmpty()) {
                builder.setMessage(NO_PAIR);
            } else {
                builder.setMessage(currentPair.toString());
            }
            alterShow(builder);
        } catch (Exception e) {
            Log.d("error",e.getMessage());
            builder.setMessage(EXCEPTION);
            alterShow(builder);
        }
    }

    private void alterShow(AlertDialog.Builder builder) {
        AlertDialog alert1 = builder.create();
        alert1.show();
    }

    private Intent prepareIntent(String myResult) throws JSONException {
        pairService.getPair(myResult);
        Intent intent = new Intent(context,RoomActivity.class);
        intent.putExtra("qrResult",myResult);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        return intent;
    }
}
