package Server.Controller;

import Connect.StreamSocket;
import DAO.CommentDAO;
import Server.ObjectGson.GsonForClient.*;
import Server.ObjectGson.GsonForServer.SV_CheckUpdate;
import Server.ObjectGson.GsonForServer.SV_listComment;
import com.google.gson.Gson;

import java.net.Socket;

public class CommentController {
    public static void responeAllComment(Socket socket) throws Exception { // tra tat va cac comment theo truyen
        Gson gson = new Gson();

        StreamSocket.checkConnect(socket);

        //doc du lieu can thiet cho cau query
        String idComicsJson = StreamSocket.readGsonFromClient(socket);
        // chuyen tu json sang class
        CL_IdComics idComics = gson.fromJson(idComicsJson,CL_IdComics.class);

        SV_listComment allComments = CommentDAO.selectAllCommentByIdComics(idComics.getIdComics());
        new StreamSocket<SV_listComment>().sendDataToCLient(socket,allComments);
    }

    public static void updateNumberOflike(Socket socket) { //cap nhat lai so luot like
        Gson gson = new Gson();
        StreamSocket.checkConnect(socket);
        // doc du lieu lan 2
        String idCommentJson = StreamSocket.readGsonFromClient(socket);

        StreamSocket.checkConnect(socket);
        //doc du lieu lan 3
        String numberOfLikeJson = StreamSocket.readGsonFromClient(socket);

        CL_IdComment idCommentClass = gson.fromJson(idCommentJson,CL_IdComment.class);
        CL_NumberOfLike numberOfLikeClass = gson.fromJson(numberOfLikeJson,CL_NumberOfLike.class);

        SV_CheckUpdate statusUpdate = CommentDAO.updateNumberOfLike(idCommentClass.getIdComment(), numberOfLikeClass.getLike());

        new StreamSocket<SV_CheckUpdate>().sendDataToCLient(socket,statusUpdate);
    }
    public static void updateNumberOfDislike(Socket socket) { // cap nhat lai so luot dislike
        Gson gson = new Gson();
        StreamSocket.checkConnect(socket);
        // doc du lieu lan 2
        String idCommentJson = StreamSocket.readGsonFromClient(socket);

        StreamSocket.checkConnect(socket);

        //doc du lieu lan 3
        String numberOfDislikeJson = StreamSocket.readGsonFromClient(socket);

        CL_IdComment idCommentClass = gson.fromJson(idCommentJson,CL_IdComment.class);
        CL_NumberOfDislike numberOfDislike = gson.fromJson(numberOfDislikeJson,CL_NumberOfDislike.class);

        SV_CheckUpdate statusUpdate = CommentDAO.updateNumberOfDislike(idCommentClass.getIdComment(), numberOfDislike.getDislike());

        new StreamSocket<SV_CheckUpdate>().sendDataToCLient(socket,statusUpdate);
    }
    public static void createNewComment(Socket socket) { // cap nhat lai so luot dislike
        Gson gson = new Gson();
        StreamSocket.checkConnect(socket);
        // doc du lieu lan 2
        String commentInformation = StreamSocket.readGsonFromClient(socket);

        CL_Comments commentClass = gson.fromJson(commentInformation,CL_Comments.class);

        SV_CheckUpdate statusUpdate = CommentDAO.createNewComment(commentClass.getIdUser(),commentClass.getComment(),commentClass.getIdComic());

        new StreamSocket<SV_CheckUpdate>().sendDataToCLient(socket,statusUpdate);
    }
}
