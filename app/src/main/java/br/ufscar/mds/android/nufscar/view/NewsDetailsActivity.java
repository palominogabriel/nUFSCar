package br.ufscar.mds.android.nufscar.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import br.ufscar.mds.android.nufscar.R;
import br.ufscar.mds.android.nufscar.model.News;
import br.ufscar.mds.android.nufscar.view.fragment.NewsFeedFragment;

import com.squareup.picasso.Picasso;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class NewsDetailsActivity extends AppCompatActivity {


    ArrayList<News> mAndroidMapList;
    String fileName = "FavoriteNews.bak";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            final News news = (News) extras.getSerializable(NewsFeedFragment.KEY_IDNOTICIA);

            final TextView newsTitle = (TextView) findViewById(R.id.news_details_title);
            TextView newsAuthor = (TextView) findViewById(R.id.news_details_author);
            TextView newsText = (TextView) findViewById(R.id.news_details_text);
            ImageView targetImageView = (ImageView) findViewById(R.id.news_details_image);
            String imageUrl = news.getImg_src();

            Picasso
                    .with(this)
                    .load(imageUrl)
                    .into(targetImageView);


            newsTitle.setText(news.getTitulo());
            newsAuthor.setText(news.getAutor() + " - " + news.getData());
            newsText.setText(news.getTexto());


            Button button = (Button) findViewById(R.id.adicionar_favoritos);

            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    try {
                        FileInputStream fileInputStream = openFileInput(fileName);
                        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                        mAndroidMapList = (ArrayList) objectInputStream.readObject();
                        objectInputStream.close();
                        fileInputStream.close();

                        boolean contains = false;
                        for (int i = 0; i < mAndroidMapList.size(); ++i) {
                            String idCarregado, idNoticia;

                            idCarregado = mAndroidMapList.get(i).getIdNoticia();
                            idNoticia = news.getIdNoticia();

                            if (idCarregado.equals(idNoticia))
                                contains = true;
                        }


                        if (!contains || mAndroidMapList.size() == 0) {
                            mAndroidMapList.add(news);
                            //mAndroidMapList.clear();

                            FileOutputStream fileOutputStream = openFileOutput(fileName, MODE_PRIVATE);
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                            objectOutputStream.writeObject(mAndroidMapList);
                            fileOutputStream.close();
                            objectOutputStream.close();
                        }
                    } catch (FileNotFoundException e) {
                        mAndroidMapList = new ArrayList<News>();
                        mAndroidMapList.add(news);

                        FileOutputStream fileOutputStream = null;
                        try {
                            fileOutputStream = openFileOutput(fileName, MODE_PRIVATE);
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                            objectOutputStream.writeObject(mAndroidMapList);
                            fileOutputStream.close();
                            objectOutputStream.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    }
}