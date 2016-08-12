package edu.pdx.cs410J.seyed.client;

import com.google.common.annotations.VisibleForTesting;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import edu.pdx.cs410J.AbstractAppointment;

import com.google.gwt.regexp.shared.MatchResult;
import java.util.Collection;
import java.util.Objects;

/**
 * A basic GWT class that makes sure that we can send an appointment book back from the server
 */
public class AppointmentBookGwt implements EntryPoint {
  //private final Alerter alerter;

    private TextBox ownerField;
    private TextBox descriptionField;
    private TextBox beginTimeField;
    private TextBox endTimeField;
    private TextBox ownerSearchField;
    private TextBox descriptionSearchField;
    private TextBox beginTimeSearchField;
    private TextBox endTimeSearchField;

    private Label ownerLabel = new Label("Owner Name: ");
    private Label descriptionLabel = new Label("Description: ");
    private Label beginTimeLabel = new Label("Begin Time: ");
    private Label endTimeLabel = new Label("End Time: ");
    private Label ownerSearchLabel = new Label("Owner Search: ");
    private Label descriptionSearchLabel = new Label("Description Search: ");
    private Label beginTimeSearchLabel = new Label("Begin Time Search: ");
    private Label endTimeSearchLabel = new Label("End Time Search: ");

    private String dateFormat = "(0?[1-9]|[012][0-9]|3[01])/(0?[1-9]|[12][0-9])/(\\d{4}|\\d{2}) ([0]?[0-9]|1[0-2]):[0-5][0-9] (am|AM|pm|PM)";


    @Override
    public void onModuleLoad() {
        //Appointment Tab
        ownerField = new TextBox();
        ownerField.setMaxLength(20);
        descriptionField = new TextBox();
        descriptionField.setMaxLength(50);
        beginTimeField = new TextBox();
        beginTimeField.setMaxLength(20);
        endTimeField = new TextBox();
        endTimeField.setMaxLength(20);

        //Search Tab
        ownerSearchField = new TextBox();
        ownerSearchField.setMaxLength(20);
        descriptionSearchField = new TextBox();
        descriptionSearchField.setMaxLength(50);
        beginTimeSearchField = new TextBox();
        beginTimeSearchField.setMaxLength(20);
        endTimeSearchField = new TextBox();
        endTimeSearchField.setMaxLength(20);


        //Add Appointment button
        Button buttonAdd = new Button("Add appointment");
        buttonAdd.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent clickEvent) {
                String owner = ownerField.getText();
                String description = descriptionField.getText();
                String beginTime = beginTimeField.getText();
                String endTime = endTimeField.getText();
                String missing;

                if (Objects.equals(owner, "") || owner == null){
                    missing = "Owner is missing";
                    Window.alert(missing);
                } else if ((!formatCheck(dateFormat, beginTime) || (!formatCheck(dateFormat, endTime)))) {
                    if (!formatCheck(dateFormat, beginTime)) {
                        missing = "Incorrect Begin Time\n" +
                                "use the bellow format\n" +
                                "MM/DD/YYYY HH:mm AM/PM";
                    } else {
                        missing = "Incorrect End Time\n" +
                                "use the bellow format\n" +
                                "MM/DD/YYYY HH:mm AM/PM";
                    }
                    Window.alert(missing);
                } else {
                    PingServiceAsync async = GWT.create(PingService.class);
                    async.ping(owner, description, beginTime, endTime, new AsyncCallback<AppointmentBook>() {
                        @Override
                        public void onFailure(Throwable throwable) {
                            if (throwable instanceof ThrowThis) {
                                Window.alert(throwable.toString());
                            } else {
                                Window.alert("onfailure: " + throwable.toString());
                            }
                        }

                        @Override
                        public void onSuccess(AppointmentBook appointmentBook) {
                            StringBuilder build = new StringBuilder(appointmentBook.toString());
                            Collection<Appointment> appointments = appointmentBook.getAppointments();
                            for (AbstractAppointment app : appointments) {
                                build.append("\n Appointment: ");
                                build.append(app);
                            }
                            Window.alert(build.toString());
                        }
                    });
                }
            }
        });


        //Search button for appointment
        Button buttonSearch = new Button("Search");
        buttonSearch.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                String owner = ownerSearchField.getText();
                String description = descriptionSearchField.getText();
                String beginTime = beginTimeSearchField.getText();
                String endTime = endTimeSearchField.getText();
                String missing;

                if(Objects.equals(owner, "")) {
                    missing = "Owner is missing";
                    Window.alert(missing);
                } else if ((!formatCheck(dateFormat, beginTime) || (!formatCheck(dateFormat, endTime)))) {
                    if (!formatCheck(dateFormat, beginTime)) {
                        missing = "Incorrect Begin Time\n" +
                                "use the bellow format\n" +
                                "MM/DD/YYYY HH:mm AM/PM";
                    } else {
                        missing = "Incorrect End Time\n" +
                                "use the bellow format\n" +
                                "MM/DD/YYYY HH:mm AM/PM";
                    }
                    Window.alert(missing);
                } else {
                    PingServiceAsync async = GWT.create(PingService.class);
                    async.pingSearch(owner, description, beginTime, endTime, new AsyncCallback<AppointmentBook>() {
                        @Override
                        public void onFailure(Throwable throwable) {
                            Window.alert(throwable.toString());
                        }

                        @Override
                        public void onSuccess(AppointmentBook appointmentBook) {
                            StringBuilder build = new StringBuilder(appointmentBook.toString());
                            Collection<Appointment> appointments = appointmentBook.getAppointments();
                            for (AbstractAppointment app : appointments) {
                                build.append("\n Appointment: ");
                                build.append(app);
                            }
                            Window.alert(build.toString());
                        }
                    });

                }

            }
        });


        //Display all button
        Button displayAll = new Button("Display all");
        displayAll.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                PingServiceAsync async = GWT.create(PingService.class);

                async.ping(new AsyncCallback<AppointmentBook>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        Window.alert(throwable.toString());
                    }

                    @Override
                    public void onSuccess(AppointmentBook appointmentBook) {
                        StringBuilder build = new StringBuilder(appointmentBook.toString());
                        Collection<Appointment> appointments = appointmentBook.getAppointments();
                        for (AbstractAppointment app : appointments) {
                            build.append("\n Appointment: ");
                            build.append(app);
                        }
                        Window.alert(build.toString());
                    }
                });
            }
        });


        //UI
        VerticalPanel owneradd = new VerticalPanel();
        owneradd.getElement().getStyle().setPadding(20, Style.Unit.PX);

        //Adding new appointment
        owneradd.add(ownerLabel);
        owneradd.add(ownerField);
        owneradd.add(descriptionLabel);
        owneradd.add(descriptionField);
        owneradd.add(beginTimeLabel);
        owneradd.add(beginTimeField);
        owneradd.add(endTimeLabel);
        owneradd.add(endTimeField);
        owneradd.add(buttonAdd);


        //Searching for appointments
        VerticalPanel search = new VerticalPanel();
        search.getElement().getStyle().setPadding(20, Style.Unit.PX);

        search.add(ownerSearchLabel);
        search.add(ownerSearchField);
        search.add(descriptionSearchLabel);
        search.add(descriptionSearchField);
        search.add(beginTimeSearchLabel);
        search.add(beginTimeSearchField);
        search.add(endTimeSearchLabel);
        search.add(endTimeSearchField);
        search.add(buttonSearch);
        search.add(displayAll);

        TabPanel tp = new TabPanel();
        tp.add(owneradd, "Add appointment");
        tp.add(search, "Search");
        tp.add(new HTML(readMe()), "Help");
        tp.selectTab(0);


        RootPanel.get().add(tp);

    }

    private static String readMe() {
        String build = "\n" +
                "Project 5 README\n" +
                "Seyed Nima Sajadpour\n" +
                "This program will deal with the appointment books and theirinfo\n" +
                "There are two command line commands which can be used\n" +
                "As well as the inputted information should be in the below format\n" +
                "---------------\n" +
                "usage: java edu.pdx.edu.cs410J.<login-id>.Project3 [options] <args>\n" +
                "args are (in this order):\n" +
                "owner             - The person whose owns the appt book\n" +
                "description       - A description of the appointment\n" +
                "beginTime         - When the appt begins (12-hour time)\n" +
                "endTime           - When the appt ends (12-hour time)\n" +
                "-------------\n" +
                "Options are (options may appear in any order\n" +
                "Date and time should be in the format: mm/dd/yyyy hh:mm \n";

        return build;
    }


    private boolean formatCheck(String dateFormat, String time) {
        RegExp regex = RegExp.compile(dateFormat);
        MatchResult match = regex.exec(time);
        return match != null && match.getGroup(0).equals(time);
    }
    }
