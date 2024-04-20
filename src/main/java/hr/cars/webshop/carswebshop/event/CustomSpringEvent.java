package hr.cars.webshop.carswebshop.event;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class CustomSpringEvent extends ApplicationEvent {

    private String message;

    public CustomSpringEvent(Object source, String message) {
        super(source);
        this.message = message;
    }
}
