package Server.ComicServer;

import Connect.StreamSocket;
import DAO.CategoryDAO;
import DAO.FollowDAO;
import DAO.UserDAO;
import Server.Controller.*;
import Server.ObjectGson.GsonForClient.CL_Request;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;

public class ClientHandler {
    public static void ExecuteClientRequest(Socket socket){
        try {
            Gson gson = new Gson();
            //doc du lieu
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("ggd12");
            String clientRequest = fromClient.readLine();
            System.out.println(clientRequest);
            //chuyen doi du lieu gson thanh doi tuong java va anh xa vao model
            CL_Request clRequest = gson.fromJson(clientRequest, CL_Request.class);
            System.out.println(clRequest.getRequest());

            //thuc hien request
            switch (clRequest.getRequest()) {
                case "/get/allcomic": {
                    try {
                        ComicController.responeALLComics(socket);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "/get/comicInformationByNameComics": {
                    try {

                        ComicController.responeComicInformationByNameComics(socket);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "/get/viewByIdComics": {
                    try {
                        StatisticsController.responeAllViews(socket);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "/get/IdCategoryByIdComics": {
                    try {
                        CategoryController.responeIdCategoryByIdComics(socket);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "/get/CategoryNameByIdCategory": {
                    try {
                        CategoryController.responeCategoryNameByIdCategory(socket);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "/get/AllCommentByIdComics": {
                    try {
                        CommentController.responeAllComment(socket);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "/get/InforUserForComment": {
                    try {
                        UserController.responeInforUserForComment(socket);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "/update/numberOfLike": {
                    try {
                        CommentController.updateNumberOflike(socket);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "/update/numberOfDislike": {
                    try {
                        CommentController.updateNumberOfDislike(socket);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "/update/newComment": {
                    try {
                        CommentController.createNewComment(socket);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "/get/AllImagesOfChapter": {
                    try {
                        ChapterController.responeAllImagesOfChapter(socket);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "/register/new/account": {
                    try {
                        RegisterController.resisterNewAcount(socket);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "/login/account": {
                    try {
                        LoginController.checkLogin(socket);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "/get/otp": {
                    try {
                        LoginController.sendOtp(socket);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "/change/password": {
                    try {
                        LoginController.changePassword(socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "/get/all/comics": {
                    try {
                        ListComicsController.getAllComic(socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "/get/all/view/comics": {
                    try {
                        ListComicsController.getAllNumView(socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "/add/new/comic": {
                    try {

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "/update/AllView": {
                    try {
                        StatisticsController.updateView(socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "/update/addNewFollow": {
                    try {
                        FollowController.addNewFollow(socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "/update/deleteFollow": {
                    try {
                        FollowController.deletedFollow(socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "/check/statusFollow": {
                    try {
                        FollowController.checkFollow(socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "/update/ChapterHistory": {
                    try {
                        HistoryController.updateChapterHistory(socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "/get/AllComicsFollow": {
                    try {
                        FollowController.responeComicsFollowInformationByIdUser(socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "/get/AllComicsHistory": {
                    try {
                        HistoryController.responeComicsHistoryInformationByIdUser(socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "/get/AllComicsByCategory": {
                    try {
                        CategoryController.responeAllComicsByCategory(socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "/get/AllCategory": {
                    try {
                        CategoryController.selectAllCategory(socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "/send/notification": {
                    try {
                        NotificationController.sendNotification(socket);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                case "/get/lastReadChapter": {
                    try {
                        HistoryController.responelastChapter(socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "/update/ExperienceAndLevelUser": {
                    try {
                        UserController.updateExperienceAndLevelUser(socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "/get/AllInformationUser": {
                    try {
                        UserController.responeUserinformaitonByIdUser(socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "/update/StoryUser": {
                    try {
                        UserController.updateStoryUserByIdUser(socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "/update/ComicByUser": {
                    try {
                        ComicController.upComicsByUser(socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }

                case "/get/FullnameUser": {
                    try {
                        UserController.responeFullnameUserByIdUser(socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "/get/TopUser": {
                    try {
                        UserController.responeListTopUser(socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "/get/TopComics": {
                    try {
                        ComicController.responeTopComics(socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "/get/AllNotificaitons": {
                    try {
                        NotificationController.responeAllNotification(socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "/delete/Notification": {
                    try {
                        NotificationController.deleteNotification(socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "/update/StatusNotification": {
                    try {
                        NotificationController.updateStatus(socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "/get/AllComicsUploadByUser": {
                    try {
                        ComicController.responeAllComicsByUsername(socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "/get/AvatarUser": {
                    try {
                        UserController.updateAvatarUser(socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
