/**
 * This class represents the model of the table "SESSION" of the database.
 * Here the data of the user's sessions is handled.
 *
 * This class implements the Serializable interface, which means that the object can be
 * converted into a heap of bytes and can later be retrieved, making it easier for us
 * to send information through intents.
 */

package com.lavv.crossfitapp.dblogic;

import java.io.Serializable;

public class Session implements Serializable{
    private int id;
    private String date_session;
    private String comment;
    private String movements;

    /**
     *  This is the class constructor, here we initialize a session
     * @param id an Integer that represents the id of the session
     * @param date_session an String with the date and time of the session expressed in the following format YYYY-MM-DD HH:MM:SS
     * @param comment an String with the comment of the session
     * @param movements an Strign with all the movements done on the session
     */
    public Session(int id, String date_session, String comment, String movements) {
        this.id = id;
        this.date_session = date_session;
        this.comment = comment;
        this.movements = movements;
    }

    /**
     *
     * @return an Strign with all the movements done on the session
     */
    public String getMovements() {
        return movements;
    }

    /**
     *
     * @return an Integer that represents the id of the session
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return an String with the date and time of the session expressed
     * in the following format YYYY-MM-DD HH:MM:SS
     */
    public String getDate_session() {
        return date_session;
    }

    /**
     *
     * @return an String with the comment of the session
     */
    public String getComment() {
        return comment;
    }

}
