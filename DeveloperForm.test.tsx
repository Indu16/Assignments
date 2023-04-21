
import {fireEvent, render} from "@testing-library/react";
import App from "../App";
import {wait} from "@testing-library/user-event/dist/utils";
import '@testing-library/jest-dom';



test ('addDeveloper',()=> {
    const {container} = render(<App/>);
    const devCount = container.querySelectorAll('.cardList');
    let count = devCount.length;
    const firstNameField = container.querySelector("addDeveloperFirstNameInput") as HTMLInputElement;
    const LastNameField = container.querySelector("addDeveloperLastNameInput") as HTMLInputElement;
    const button = container.querySelector('addDeveloperBtn') as HTMLButtonElement;

    firstNameField.value = "Unit";
    LastNameField.value = "Test";
    fireEvent.click(button);
    wait(1000);
    expect(devCount.length).toBe(count + 1);
});
