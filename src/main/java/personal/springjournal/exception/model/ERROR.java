package personal.springjournal.exception.model;

import personal.springjournal.exception.AlreadyExisted;
import personal.springjournal.exception.DataNotFound;
import personal.springjournal.exception.ServerError;

public class ERROR {

    public final static DataNotFound DATA_NOT_FOUND =
            new DataNotFound("Data does not exist");
    public final static ServerError SERVER_WENT_WRONG =
            new ServerError("Server went wrong");
    public final static AlreadyExisted DATA_ALREADY_EXISTED =
            new AlreadyExisted("Already existed");
}
