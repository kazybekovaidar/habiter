CREATE TABLE IF NOT EXISTS habiter.standard_habit_card
(
    habit_id SERIAL NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(250) NOT NULL,
    goal INT NOT NULL,
    unit VARCHAR(255) NOT NULL,
    user_id SERIAL NOT NULL,
    completed BOOLEAN NOT NULL DEFAULT false,
    progress INT NOT NULL DEFAULT 0,
    PRIMARY KEY (habit_id),
    CONSTRAINT fk_user_habit FOREIGN KEY (user_id) REFERENCES habiter.user (user_id)
    );
