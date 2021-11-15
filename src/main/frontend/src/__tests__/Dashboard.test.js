import { render, screen } from '@testing-library/react';
import Dashboard from '../components/Dashboard';

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

test('renders Dashboard component', () => {
    render(<Dashboard/>);
    const headerElement = screen.getByText('CleanSweep Portal');
    expect(headerElement).toBeInTheDocument();
});
