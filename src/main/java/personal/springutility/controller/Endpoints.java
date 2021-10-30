package personal.springutility.controller;

public class Endpoints {

    public static final String ADD_PAGE = "add/{userId}";
    public static final String FIND_ALL = "findall/{userId}/{createdPageId}";
    public static final String FIND_ONE = "find/{createdPageId}/";
    public static final String DELETE_ONE = "delete/{userId}/{createdPageId}/{pageId}";
    public static final String UPDATE_PAGE = "update/{userId}/{createdPageId}/";
}
