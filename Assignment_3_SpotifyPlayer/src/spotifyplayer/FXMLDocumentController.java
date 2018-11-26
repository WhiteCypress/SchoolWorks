/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spotifyplayer;

import com.sun.javafx.collections.ObservableListWrapper;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author bergeron
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    TableView tracksTableView;

    @FXML
    Slider trackSlider;

    @FXML
    Label artistLabel;

    @FXML
    Label albumLabel;

    @FXML
    Label timeLabel;

    @FXML
    ProgressIndicator progress;

    @FXML
    TextField searchTextField;

    @FXML
    ImageView albumImage;

    @FXML
    Button playButton;

    @FXML
    Button leftButton;

    @FXML
    Button rightButton;

    @FXML
    MenuItem saveItem;

    Button lastPlayButtonPressed;
    Album album;

    ScheduledExecutorService sliderExecutor = null;
    MediaPlayer mediaPlayer = null;

    ArrayList<Album> albums = null;
    ArrayList<Album> oldAlbums = null;
    int currentAlbumIndex = 0;

    @FXML
    private void saveClicked(ActionEvent event) {
        Image img = new Image(album.getImageURL());
        save(img);
    }

    @FXML
    private void searchTextFieldHandler(Event event) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.submit(new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                oldAlbums = albums;
                try {
                    searchAlbumsFromArtist(searchTextField.getText());
                } catch (Exception e) {
                    artistLabel.setText("Error");
                    albumLabel.setText("Invalid Artist Name");
                }
                return null;
            }

            @Override
            protected void succeeded() {

                try {
                    for (int i = 0; i < albums.get(currentAlbumIndex).getTracks().size(); i++) {
                        displayAlbum(currentAlbumIndex);
                    }
                } catch (Exception e) {
                    albums = oldAlbums;
                    displayAlbum(currentAlbumIndex);

                    artistLabel.setText("Error");
                    albumLabel.setText("Invalid Artist Name");
                }
            }

            @Override
            protected void cancelled() {
                artistLabel.setText("Error");
                albumLabel.setText("Invalid Artist Name");
            }
        }
        );
    }

    @FXML
    private void rightButtonClick(ActionEvent event) {
        if(lastPlayButtonPressed != null && lastPlayButtonPressed.getText().equals("Stop")){
            lastPlayButtonPressed.fire();
        }
        currentAlbumIndex++;

        if (currentAlbumIndex >= albums.size()) {
            currentAlbumIndex -= albums.size();
        }

        displayAlbum(currentAlbumIndex);
    }

    @FXML
    private void leftButtonClick(ActionEvent event) {
        if(lastPlayButtonPressed != null && lastPlayButtonPressed.getText().equals("Stop")){
            lastPlayButtonPressed.fire();
        }
        currentAlbumIndex--;

        if (currentAlbumIndex < 0) {
            currentAlbumIndex += albums.size();
        }

        displayAlbum(currentAlbumIndex);
    }

    @FXML
    private void playButtonClick(ActionEvent event) {
        if (playButton.getText().equals("Stop")) {
            mediaPlayer.pause();
            sliderExecutor.shutdownNow();
            sliderExecutor = null;
            playButton.setText("Play");
            lastPlayButtonPressed.setText("Play");
        } else {
            mediaPlayer.play();
            playButton.setText("Stop");
            lastPlayButtonPressed.setText("Stop");
            if (sliderExecutor != null) {
                sliderExecutor.shutdownNow();
            }
            sliderExecutor = Executors.newSingleThreadScheduledExecutor();
            sliderExecutor.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            // Move slider
                            trackSlider.setValue(trackSlider.getValue() + 0.001);
                        }
                    });
                }
            }, 1, 1, TimeUnit.MILLISECONDS);
        }
    }

    // Other Fields...
    private void playPauseTrackPreview(Button source, String trackPreviewUrl) {
        try {
            if (source.getText().equals("Play")) {
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                }

                source.setText("Stop");
                playButton.setText("Stop");
                playButton.setDisable(false);
                trackSlider.setDisable(false);
                trackSlider.setValue(0.0);

                // Start playing music
                Media music = new Media(trackPreviewUrl);
                mediaPlayer = new MediaPlayer(music);
                mediaPlayer.setStartTime(new Duration(0));
                mediaPlayer.play();

                // This runnable object will be called
                // when the track is finished or stopped
                Runnable stopTrackRunnable = new Runnable() {
                    @Override
                    public void run() {
                        source.setText("Play");
                        if (sliderExecutor != null) {
                            sliderExecutor.shutdownNow();
                        }
                    }
                };
                mediaPlayer.setOnEndOfMedia(stopTrackRunnable);
                mediaPlayer.setOnStopped(stopTrackRunnable);

                // Schedule the slider to move right every second
                sliderExecutor = Executors.newSingleThreadScheduledExecutor();
                sliderExecutor.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        // We can't update the GUI elements on a separate thread... 
                        // Let's call Platform.runLater to do it in main thread!!
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                // Move slider

                                trackSlider.setValue(trackSlider.getValue() + 0.001);
                            }
                        });
                    }
                }, 1, 1, TimeUnit.MILLISECONDS);

                trackSlider.valueProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                        if (newValue == null) {
                            return;//To change body of generated methods, choose Tools | Templates.
                        }
                        timeLabel.setText(String.format("%.2f", newValue.doubleValue() / 100) + "");
                    }
                });

            } else {
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    playButton.setText("Play");
                }
            }

        } catch (Exception e) {
            System.err.println("error with slider executor... this should not happen!");
        }
    }

    private void displayAlbum(int albumNumber) {
        // TODO - Display all the informations about the album
        //
        //        Artist Name 
        //        Album Name
        //        Album Cover Image
        //        Enable next/previous album buttons, if there is more than one album

        // Display Tracks for the album passed as parameter
        if (albumNumber >= 0 && albumNumber < albums.size()) {
            currentAlbumIndex = albumNumber;
            album = albums.get(albumNumber);

            // Set tracks
            ArrayList<TrackForTableView> tracks = new ArrayList<>();
            for (int i = 0; i < album.getTracks().size(); ++i) {
                TrackForTableView trackForTable = new TrackForTableView();
                Track track = album.getTracks().get(i);
                trackForTable.setTrackNumber(track.getNumber());
                trackForTable.setTrackTitle(track.getTitle());
                trackForTable.setTrackPreviewUrl(track.getUrl());
                tracks.add(trackForTable);
            }
            tracksTableView.setItems(new ObservableListWrapper(tracks));

            trackSlider.setDisable(true);
            trackSlider.setValue(0.0);

            artistLabel.setText(album.getArtistName());
            albumLabel.setText(album.getAlbumName());
            albumImage.setImage(new Image(album.getImageURL()));

            if (albums.size() == 1) {
                rightButton.setDisable(true);
                leftButton.setDisable(true);
            } else {
                rightButton.setDisable(false);
                leftButton.setDisable(false);
            }
        }
    }

    private void searchAlbumsFromArtist(String artistName) {
        // TODO - Make sure this is not blocking the UI
        progress.setVisible(true);
        try {
            currentAlbumIndex = 0;
            String artistId = SpotifyController.getArtistId(artistName);
            albums = SpotifyController.getAlbumDataFromArtist(artistId);
            progress.setVisible(false);
        } catch (Exception e) {
            progress.setVisible(false);
            artistLabel.setText("Invalid Artist Name");
        }
    }

    public void save(Image img) {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(img, null);

        JFileChooser fc = new JFileChooser();                                   //save the file in the selected place with a selected name
        fc.setCurrentDirectory(new File("image"));
        FileNameExtensionFilter ff = new FileNameExtensionFilter("image", "jpg", "png", "BMP");
        fc.setFileFilter(ff);
        int returnValue = fc.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                ImageIO.write(bufferedImage, "png", file);
            } catch (Exception e) {
                artistLabel.setText("Error");
                albumLabel.setText("Error occured while saving");
            }

        }
    }

    public void shutdown() {
        if (sliderExecutor != null) {
            sliderExecutor.shutdown();
        }
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        playButton.setDisable(true);
        progress.setVisible(false);
        // Setup Table View
        TableColumn<TrackForTableView, Number> trackNumberColumn = new TableColumn("#");
        trackNumberColumn.setCellValueFactory(new PropertyValueFactory("trackNumber"));

        TableColumn trackTitleColumn = new TableColumn("Title");
        trackTitleColumn.setCellValueFactory(new PropertyValueFactory("trackTitle"));
        trackTitleColumn.setPrefWidth(250);

        TableColumn playColumn = new TableColumn("Preview");
        playColumn.setCellValueFactory(new PropertyValueFactory("trackPreviewUrl"));
        Callback<TableColumn<TrackForTableView, String>, TableCell<TrackForTableView, String>> cellFactory = new Callback<TableColumn<TrackForTableView, String>, TableCell<TrackForTableView, String>>() {
            @Override
            public TableCell<TrackForTableView, String> call(TableColumn<TrackForTableView, String> param) {
                final TableCell<TrackForTableView, String> cell = new TableCell<TrackForTableView, String>() {
                    final Button playButton = new Button("Play");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        if (item != null && item.equals("") == false) {
                            playButton.setOnAction(event -> {
                                lastPlayButtonPressed = playButton;
                                playPauseTrackPreview(playButton, item);
                            });

                            setGraphic(playButton);
                        } else {
                            setGraphic(null);
                        }

                        setText(null);
                    }
                };

                return cell;
            }
        };
        playColumn.setCellFactory(cellFactory);
        tracksTableView.getColumns().setAll(trackNumberColumn, trackTitleColumn, playColumn);

        // When slider is released, we must seek in the song
        trackSlider.setOnMouseReleased(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (mediaPlayer != null) {
                    mediaPlayer.seek(Duration.millis(trackSlider.getValue()));
                }
            }
        });

        // Initialize GUI
        searchAlbumsFromArtist("pink floyd");
        displayAlbum(0);
    }
}
