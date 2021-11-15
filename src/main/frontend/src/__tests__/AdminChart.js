import { render, screen } from '@testing-library/react';
import AdminChart from '../components/AdminChart';

let originalError;

beforeEach(() => {
    jest.spyOn(console, 'error')
    // @ts-ignore jest.spyOn adds this functionallity
    console.error.mockImplementation(() => null);
});

afterEach(() => {
    // @ts-ignore jest.spyOn adds this functionallity
    console.error.mockRestore()
})

test('renders AdminChart component Button Group', () => {
    render(<AdminChart />);
    let allButtons = ['Visualize Robot', 'Recharging', 'Path', 'Activity Log']

    allButtons.forEach(button => {
        let localButtonText = screen.getByText(button);
        expect(localButtonText).toBeInTheDocument();
    })
});
